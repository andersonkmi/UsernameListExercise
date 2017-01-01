package org.soaring.emailage.data;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class ReportedEmailRowMapper implements RowMapper<ReportedEmail> {
    public ReportedEmail mapRow(ResultSet rs, int row) throws SQLException {
        ReportedEmail email = new ReportedEmail();
        String emailAddress = rs.getString("email");
        Integer id = rs.getInt("idMotivo");
        String description = rs.getString("descricao");
        Long transactionId = rs.getLong("trans_id");
        Date reportDate = rs.getDate("data");

        email.setEmail(emailAddress);
        email.setReportCause(FControlFraudReportCause.findById(id));
        email.setFraudDescription(description);
        email.setTransactionId(transactionId);
        Calendar reportedDate = Calendar.getInstance();
        reportedDate.setTime(reportDate);
        return email;
    }
}
