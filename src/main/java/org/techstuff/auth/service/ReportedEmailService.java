package org.soaring.emailage.service;

import org.apache.log4j.Logger;
import org.soaring.emailage.data.ReportedEmail;
import org.soaring.emailage.data.ReportedEmailDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

@Service
class ReportedEmailService {
    private static final Logger logger = Logger.getLogger(ReportedEmailService.class);

    @Resource
    private ReportedEmailDAO reportedEmailDAO;

    public List<ReportedEmail> findReportedBuyerEmails(Calendar startDate, Calendar endDate) {
        logger.info("Calling the reported email service");
        return reportedEmailDAO.findReportedBuyerEmailsByAnalysisDate(startDate, endDate);
    }

    public List<ReportedEmail> findReportedDeliveryEmails(Calendar startDate, Calendar endDate) {
        logger.info("Calling the reported email service");
        return reportedEmailDAO.findReportedDeliveryEmailsByAnalysisDate(startDate, endDate);
    }

    public List<ReportedEmail> findReportedPayerEmails(Calendar startDate, Calendar endDate) {
        logger.info("Calling the reported email service");
        return reportedEmailDAO.findReportedPayerEmailsByAnalysisDate(startDate, endDate);
    }
}
