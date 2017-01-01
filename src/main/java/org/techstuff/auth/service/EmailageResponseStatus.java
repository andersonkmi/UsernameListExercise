package org.soaring.emailage.service;

public class EmailageResponseStatus {
    private String status;
    private EmailageErrorCode errorCode;
    private String email;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setErrorCode(EmailageErrorCode code) {
        errorCode = code;
    }

    public EmailageErrorCode getErrorCode() {
        return errorCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
