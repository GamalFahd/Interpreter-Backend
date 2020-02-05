package com.sprintcodes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Execution request taking too long")
public class ExceptionTimeOut extends NotebookException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
