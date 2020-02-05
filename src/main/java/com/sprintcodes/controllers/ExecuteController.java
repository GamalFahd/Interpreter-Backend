package com.sprintcodes.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprintcodes.exceptions.NotebookException;
import com.sprintcodes.models.NotebookExecutionResponse;
import com.sprintcodes.models.NotebookExecutionRequest;
import com.sprintcodes.models.NotebookRequest;
import com.sprintcodes.models.NotebookResponse;
import com.sprintcodes.services.InterpreterRequestParsingService;
import com.sprintcodes.services.InterpreterService;
import com.sprintcodes.services.InterpreterServiceFactory;
import com.sprintcodes.validation.CorrectRequest;

@RestController
@Validated
public class ExecuteController {

    @Autowired
    private InterpreterRequestParsingService InterpreterRequestParsingService;

    @Autowired
    private InterpreterServiceFactory interpreterServiceFactory;

    @PostMapping("/execute")
    public ResponseEntity<NotebookResponse> execute(@CorrectRequest @RequestBody NotebookRequest NotebookRequest, HttpSession httpSession) throws NotebookException {
        
    	NotebookExecutionRequest request = InterpreterRequestParsingService.parseInterpreterRequest(NotebookRequest);
        InterpreterService interpreterService = interpreterServiceFactory.getInterpreterService(request.getLanguage());
        String sessionId = NotebookRequest.getSessionId() != null ? NotebookRequest.getSessionId() : httpSession.getId();
        request.setSessionId(sessionId);
        NotebookExecutionResponse executionResponse = interpreterService.execute(request);
        NotebookResponse NotebookResponse = new NotebookResponse();
        NotebookResponse.setResponse(executionResponse.getOutput());
        NotebookResponse.setErrors(executionResponse.getErrors());
        NotebookResponse.setSessionId(sessionId);
        return ResponseEntity.ok(NotebookResponse);
    }
}
