package de.rwu.swen.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {

    public NotFoundException(Long id) {
        super("Student with ID '%d' does not exist".formatted(id));
    }

}
