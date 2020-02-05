package com.sprintcodes.models;

public class NotebookExecutionResponse {
    String output;
    String errors;

    public NotebookExecutionResponse() {
        // no args constructor
    }

    public NotebookExecutionResponse(String output, String errors) {
        this.output = output;
        this.errors = errors;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
