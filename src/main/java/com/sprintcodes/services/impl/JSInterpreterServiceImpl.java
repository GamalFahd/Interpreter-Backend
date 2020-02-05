package com.sprintcodes.services.impl;

import org.springframework.stereotype.Service;

import com.sprintcodes.models.NotebookInterpreter;
import com.sprintcodes.services.GraalVmInterpreterService;

@Service
public class JSInterpreterServiceImpl extends GraalVmInterpreterService {

    /**
     * {@inheritDoc}
     */
    @Override
    public NotebookInterpreter getInterpreterLanguage() {
        return NotebookInterpreter.JAVA_SCRIPT;
    }
}
