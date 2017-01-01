package org.soaring.emailage.service;

public enum EmailageErrorCode {

    SUCCESS(0, "Success"),
    INVALID_INPUT_PARAMETER(400, "Invalid input parameter"),
    INVALID_EXPIRED_TOKEN(401, "Invalid or expired token"),
    INVALID_OAUTH_REQUEST(403, "Invalid OAUTH request"),
    FILE_FOLDER_NOT_FOUND(404, "File or folder not found"),
    REQUEST_METHOD_NOT_EXPECTED(405, "Request method not expected"),
    CALLS_EXCEEDING_THROTTLE_LIMIT(503, "Calls are exceeding the throttle limit"),
    AUTH_ERROR_SIGNATURE_NOT_MATCH(3001, "Authentication error: the signature does not match or the user/consumer key was not found"),
    AUTH_ERROR_ACCOUNT_INACTIVE_DISABLED(3002, "Authentication error: your account status is inactive or disabled."),
    AUTH_ERROR_ACCOUNT_EXPIRED(3003, "Authentication error: your account is expired"),
    AUTH_ERROR_NOT_ENOUGH_CREDIT(3004, "Authentication error: you do not have enough query credits available."),
    AUTH_ERROR_GENERAL_ERROR(3005, "Authentication error: a general error has occurred"),
    INVALID_PARAMETER_NOT_PROVIDED(3006, "Invalid parameter: parameter not provided or empty"),
    INVALID_PARAMETER_MALFORMED(3007, "Invalid parameter: wrong or malformed parameter"),
    AUTH_ERROR_INVALID_TIMESTAMP(3008, "Authentication error: invalid timestamp"),
    AUTH_ERROR_INVALID_NONCE(3009, "Authentication error: invalid nonce"),
    INVALID_PARAMETER_PARTNER_ID(3010, "Invalid parameter: invalid partnerId provided");

    EmailageErrorCode(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static EmailageErrorCode findByCode(Integer code) {
        if(code == null) {
            return EmailageErrorCode.SUCCESS;
        }

        for(EmailageErrorCode errorCode : values()) {
            if(code == errorCode.getCode()) {
                return errorCode;
            }
        }

        return EmailageErrorCode.SUCCESS;
    }

    private Integer code;
    private String description;
}
