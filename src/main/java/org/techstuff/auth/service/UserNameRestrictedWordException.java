package org.techstuff.auth.service;

/**
 * Created by andersonkmi on 1/1/2017.
 */
public class UserNameRestrictedWordException extends Exception {
    public UserNameRestrictedWordException(String message) {
        super(message);
    }

    public UserNameRestrictedWordException(String message, Throwable exception) {
        super(message, exception);
    }
}
