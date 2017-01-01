package org.techstuff.auth.service;


public class InvalidUserNameException extends Exception {
    public InvalidUserNameException(String message) {
        super(message);
    }

    public InvalidUserNameException(String message, Throwable exception) {
        super(message, exception);
    }

    public InvalidUserNameException(Throwable exception) {
        super(exception);
    }
}
