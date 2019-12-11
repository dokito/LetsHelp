package com.dokito.letshelp.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found!")
public class UserNotFound extends RuntimeException {

    private int status;

    public UserNotFound() {
        this.status = 404;
    }

    public UserNotFound(String message) {
        super(message);
        this.status = 404;
    }

    public int getStatus() {
        return status;
    }
}
