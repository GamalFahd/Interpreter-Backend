package com.sprintcodes.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprintcodes.models.NotebookRequest;
import com.sprintcodes.models.NotebookResponse;
import com.sprintcodes.validation.CorrectRequest;

@RestController
@Validated
public class ExecuteController {
	
	
	@PostMapping("/execute")
	public ResponseEntity<NotebookResponse> execute(@CorrectRequest @RequestBody NotebookRequest request) {
		NotebookResponse response = new NotebookResponse();
		response.setResponse("Test");
		
		return ResponseEntity.ok(response);
	}

}
