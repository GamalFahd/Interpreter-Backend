package com.sprintcodes.services.impl;

import org.springframework.stereotype.Service;

import com.sprintcodes.models.NotebookInterpreter;
import com.sprintcodes.services.GraalVmInterpreterService;

@Service
public class PythonInterpreterServiceImpl extends GraalVmInterpreterService {

    /**
     * {@inheritDoc}
     */
    @Override
    public NotebookInterpreter getInterpreterLanguage() {
        return NotebookInterpreter.PYTHON;
    }

}
