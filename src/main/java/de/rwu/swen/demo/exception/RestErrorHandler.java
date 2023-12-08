package de.rwu.swen.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestErrorHandler {

    /**
     * Handles {@link NotFoundException}s thrown in the {@link de.rwu.swen.demo.controller.StudentController}.
     * Returns HTTP Status 404 - not found and a JSON containing the exception's error message.
     *
     * @param e the exception to be handled
     *
     * @return an error response with the exception's message.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Object processValidationError(NotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }
}

/**
 * Helper record which is being transformed to JSON that is returned by the HTTP request.
 */
record ErrorResponse(String errorMsg) {}