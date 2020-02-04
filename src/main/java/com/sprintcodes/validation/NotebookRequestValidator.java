package com.sprintcodes.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.sprintcodes.models.NotebookRequest;

public class NotebookRequestValidator implements ConstraintValidator<CorrectRequest, NotebookRequest> {

    private static final String NOTEBOOK_REQUEST_PATTERN = "%(\\w+)\\s+(.*)";

    @Override
    public boolean isValid(NotebookRequest request, ConstraintValidatorContext context) {
        if (request == null) return true;
        if (request.getCode() == null || request.getCode().isEmpty()) {
            context.buildConstraintViolationWithTemplate("You have to specify your request code !")
                    .addPropertyNode("code")
                    .addConstraintViolation();
            return false;
        }

        if (!matchesWithPatternTest(request.getCode())) {
            context.buildConstraintViolationWithTemplate("Invalid request code format. ")
                    .addPropertyNode("code");
            return false;
        }

        return true;
    }

    boolean matchesWithPatternTest(String code) {
        if (code == null) return false;
        return code.matches(NOTEBOOK_REQUEST_PATTERN);
    }
}
