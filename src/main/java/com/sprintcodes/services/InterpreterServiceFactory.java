package com.sprintcodes.services;

import com.sprintcodes.exceptions.LanguageNotSupportedException;
import com.sprintcodes.models.NotebookInterpreter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;


@Service
public class InterpreterServiceFactory {

    private Map<NotebookInterpreter, InterpreterService> interpreterServiceMap = new EnumMap<>(NotebookInterpreter.class);

    @Autowired
    public InterpreterServiceFactory(List<InterpreterService> interpreterServices) {
        for (InterpreterService interpreterService: interpreterServices) {
            interpreterServiceMap.put(interpreterService.getInterpreterLanguage(), interpreterService);
        }
    }

    
    public InterpreterService getInterpreterService(String language) {
        NotebookInterpreter interpreter = NotebookInterpreter.getInterpreterFromLanguageName(language);
        if (interpreter == null || !interpreterServiceMap.containsKey(interpreter)) {
            throw new LanguageNotSupportedException();
        }
        return interpreterServiceMap.get(interpreter);
    }
}
