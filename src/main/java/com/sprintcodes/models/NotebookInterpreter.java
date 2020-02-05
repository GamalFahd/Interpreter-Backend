package com.sprintcodes.models;

public enum NotebookInterpreter {
	PYTHON("python"),
	JAVA_SCRIPT("js");

	private String name;
	NotebookInterpreter(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static NotebookInterpreter getInterpreterFromLanguageName(String language) {
		for (NotebookInterpreter interpreter : NotebookInterpreter.values()) {
			if (interpreter.name.equalsIgnoreCase(language)) {
				return interpreter;
			}
		}

		return null; // add default ?
	}
}
