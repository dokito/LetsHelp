package com.dokito.letshelp.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Person in need not found!")
public class PersonInNeedNotFound extends RuntimeException {

    private int status;

    public PersonInNeedNotFound() {
        this.status = 404;
    }

    public PersonInNeedNotFound(String message) {
        super(message);
        this.status = 404;
    }

    public int getStatus() {
        return status;
    }
}
