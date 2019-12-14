package com.dokito.letshelp.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND, reason = "User already participate in this event")
public class UserAlreadyParticipateInEvent extends RuntimeException {
    private int status;

    public UserAlreadyParticipateInEvent() {
        this.status = 302;
    }

    public UserAlreadyParticipateInEvent(String message) {
        super(message);
        this.status = 302;
    }

    public int getStatus() {
        return status;
    }
}
