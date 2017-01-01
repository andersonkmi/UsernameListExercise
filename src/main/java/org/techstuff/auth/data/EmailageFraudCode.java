package org.soaring.emailage.data;

public enum EmailageFraudCode {
    CARD_NOT_PRESENT_FRAUD(1, "Card not present fraud"),
    CUSTOMER_DISPUTE_CBK(2, "Customer dispute - chargeback"),
    FIRST_PARTY_FRAUD(3, "First party fraud"),
    FIRST_PAYMENT_DEFAULT(4, "First payment default"),
    IDENTITY_THEFT_FRAUD_APPLICATION(5, "Identity theft - fraud application"),
    IDENTITY_THEFT_ACCOUNT_TAKEOVER(6, "Identity theft - account takeover"),
    SUSPECTED_FRAUD(7, "Suspected fraud - not confirmed"),
    SYNTHETIC_ID(8, "Synthetic id"),
    OTHER(9, "Other");

    private Integer code;
    private String description;

    EmailageFraudCode(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static EmailageFraudCode findByCode(Integer code) {
        if(code == null) {
            return EmailageFraudCode.OTHER;
        }

        for(EmailageFraudCode item : values()) {
            if(code == item.getCode()) {
                return item;
            }
        }
        return EmailageFraudCode.OTHER;
    }
}
