package com.dokito.letshelp.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Fundraising not found!")
public class FundrasingNotFound extends RuntimeException {
    private int status;

    public FundrasingNotFound() {
        this.status = 404;
    }

    public FundrasingNotFound(String message) {
        super(message);
        this.status = 404;
    }

    public int getStatus() {
        return status;
    }
}
