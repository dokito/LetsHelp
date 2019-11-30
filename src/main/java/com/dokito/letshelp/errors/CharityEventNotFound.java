package com.dokito.letshelp.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Charity event not found!")
public class CharityEventNotFound extends RuntimeException {

    private int status;

    public CharityEventNotFound() {
        this.status = 404;
    }

    public CharityEventNotFound(String message) {
        super(message);
        this.status = 404;
    }

    public int getStatus() {
        return status;
    }
}
