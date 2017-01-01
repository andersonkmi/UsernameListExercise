package org.soaring.emailage.data;

import java.util.Calendar;

public class ReportedEmail {
    private String email;
    private String fraudDescription;
    private FControlFraudReportCause reportCause;
    private Long transactionId;
    private Calendar reportDate;

    public void setTransactionId(Long id) {
        transactionId = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setFraudDescription(String description) {
        fraudDescription = description;
    }

    public String getFraudDescription() {
        return fraudDescription;
    }

    public void setReportCause(FControlFraudReportCause cause) {
        reportCause = cause;
    }

    public FControlFraudReportCause getReportCause() {
        return reportCause;
    }
}
