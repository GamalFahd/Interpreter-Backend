package com.sprintcodes.services;

import com.sprintcodes.exceptions.InvalidInterpreterRequestException;
import com.sprintcodes.models.NotebookExecutionRequest;
import com.sprintcodes.models.NotebookRequest;


public interface InterpreterRequestParsingService {
    
    NotebookExecutionRequest parseInterpreterRequest(NotebookRequest request) throws InvalidInterpreterRequestException;
}
