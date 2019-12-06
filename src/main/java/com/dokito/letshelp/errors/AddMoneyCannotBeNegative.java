package com.dokito.letshelp.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Money cannot be negative!")
public class AddMoneyCannotBeNegative extends RuntimeException {

    private int status;

    public AddMoneyCannotBeNegative() {
        this.status = 406;
    }

    public AddMoneyCannotBeNegative(String message) {
        super(message);
        this.status = 406;
    }

    public int getStatus() {
        return status;
    }
}
