package de.rwu.swen.demo.exception;

/**
 * Exception to be thrown if a student could not be found.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("Student with ID '%d' does not exist".formatted(id));
    }

}
