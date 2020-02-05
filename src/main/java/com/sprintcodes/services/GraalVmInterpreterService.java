package com.sprintcodes.services;

import com.sprintcodes.config.ApplicationProperties;
import com.sprintcodes.models.NotebookExecutionRequest;
import com.sprintcodes.models.NotebookExecutionContext;
import com.sprintcodes.models.NotebookExecutionResponse;
import com.sprintcodes.exceptions.LanguageNotSupportedException;
import com.sprintcodes.exceptions.ExceptionTimeOut;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public abstract class GraalVmInterpreterService implements InterpreterService {

    @Autowired
    private ApplicationProperties applicationProperties;

    private Map<String, NotebookExecutionContext> sessionContexts = new ConcurrentHashMap<>();
    /**
     * {@inheritDoc}
     */
    @Override
    public NotebookExecutionResponse execute(NotebookExecutionRequest request) {

        // Check if language supported
        if (unsupportedLanguage()) {
            throw new LanguageNotSupportedException();
        }

        NotebookExecutionContext executionContext = getContext(request.getSessionId());
        final Context context = executionContext.getContext();

        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    context.close(true);
                    executionContext.getOutputStream().close();
                    executionContext.getErrorsStream().close();
                    executionContext.setTimedOut(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, applicationProperties.getTimeOutDuration());

        try {
            context.eval(getInterpreterLanguage().getName(), request.getCode());
            timer.cancel();
            timer.purge();
            return new NotebookExecutionResponse(executionContext.getOutput(), executionContext.getErrors());
        } catch(PolyglotException e) {
            timer.cancel();
            timer.purge();
            if (e.isCancelled()) {
                // remove context
                sessionContexts.remove(request.getSessionId());
                throw new ExceptionTimeOut();
            }

            // TODO add polyglot exceptions handling ?
            return new NotebookExecutionResponse("" , e.getMessage());
        }

    }

    private boolean unsupportedLanguage() {
        try (Context tmpContext = Context.create()) {
            return !tmpContext.getEngine().getLanguages().containsKey(getInterpreterLanguage().getName());
        }
    }

    /**
     * Get Execution Context by sessionId
     * @param sessionId
     * @return
     */
    private NotebookExecutionContext getContext(String sessionId) {
        return sessionContexts.computeIfAbsent(sessionId, key -> buildContext());
    }

    private NotebookExecutionContext buildContext() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errorsStream = new ByteArrayOutputStream();
        Context context = Context.newBuilder(getInterpreterLanguage().getName()).out(outputStream).err(errorsStream)
                .build();

        return new NotebookExecutionContext(outputStream, errorsStream, context);
    }
}
