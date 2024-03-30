package edu.unimagdalena.marketplace.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestException extends RuntimeException {
    private static final String DESCRIPTION = "(400) Bad Request";

    public BadRequestException(String details) {
        super(DESCRIPTION + "." + details);
    }
}
