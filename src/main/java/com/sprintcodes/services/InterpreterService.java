package com.sprintcodes.services;

import com.sprintcodes.exceptions.NotebookException;
import com.sprintcodes.models.NotebookExecutionResponse;
import com.sprintcodes.models.NotebookInterpreter;
import com.sprintcodes.models.NotebookExecutionRequest;


public interface InterpreterService {

   
    NotebookInterpreter getInterpreterLanguage();

   
    NotebookExecutionResponse execute(NotebookExecutionRequest request) throws NotebookException;
}
