package edu.unimagdalena.marketplace.exception;

public class NotFoundException extends RuntimeException {
    private static final String DESCRIPTION = "(404) Not found";

    public NotFoundException(String details) {
        super(DESCRIPTION + "." + details);
    }
}
