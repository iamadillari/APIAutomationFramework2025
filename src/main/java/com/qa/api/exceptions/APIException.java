package com.qa.api.exceptions;

/**
 * Custom exception class for API-related errors.
 * Extends the RuntimeException class to provide unchecked exceptions
 * for handling API-specific issues.
 */
public class APIException extends RuntimeException {

    /**
     * Constructs a new APIException with the specified detail message.
     *
     * @param msg The detail message that describes the exception.
     */
    public APIException(String msg) {
        super(msg);
    }
}