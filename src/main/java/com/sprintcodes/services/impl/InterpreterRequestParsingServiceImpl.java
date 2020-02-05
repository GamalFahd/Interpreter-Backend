package com.sprintcodes.services.impl;

import com.sprintcodes.exceptions.InvalidInterpreterRequestException;
import com.sprintcodes.models.NotebookExecutionRequest;
import com.sprintcodes.models.NotebookRequest;
import com.sprintcodes.services.InterpreterRequestParsingService;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InterpreterRequestParsingServiceImpl implements InterpreterRequestParsingService {

	private static final String REQUEST_PATTERN = "%(\\w+)\\s+(.*)";
	private static final Pattern pattern = Pattern.compile(REQUEST_PATTERN);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NotebookExecutionRequest parseInterpreterRequest(NotebookRequest request) {
		Matcher matcher = pattern.matcher(request.getCode());
		if (matcher.matches()) {
			String language = matcher.group(1);
			String code = matcher.group(2);

			NotebookExecutionRequest executionRequest = new NotebookExecutionRequest();
			executionRequest.setCode(code);
			executionRequest.setLanguage(language);

			return executionRequest;
		}

		throw new InvalidInterpreterRequestException();
	}
}
