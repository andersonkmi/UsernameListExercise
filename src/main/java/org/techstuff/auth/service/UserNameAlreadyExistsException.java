package org.techstuff.auth.service;

/**
 * Created by andersonkmi on 1/1/2017.
 */
public class UserNameAlreadyExistsException extends Exception {
    public UserNameAlreadyExistsException(String message) {
        super(message);
    }

    public UserNameAlreadyExistsException(String message, Throwable exception) {
        super(message, exception);
    }
}
