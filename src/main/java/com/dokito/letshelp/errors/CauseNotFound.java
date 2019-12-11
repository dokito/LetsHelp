package com.dokito.letshelp.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Person in need not found!")
public class CauseNotFound extends RuntimeException {

    private int status;

    public CauseNotFound() {
        this.status = 404;
    }

    public CauseNotFound(String message) {
        super(message);
        this.status = 404;
    }

    public int getStatus() {
        return status;
    }
}
