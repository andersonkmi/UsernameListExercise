package org.soaring.emailage.service;

import org.apache.log4j.Logger;
import org.soaring.emailage.data.ReportedEmail;
import org.soaring.emailage.data.ReportedEmailDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmailageFraudReportService {
    private static final Logger logger = Logger.getLogger(EmailageFraudReportService.class);

    @Resource
    private ReportedEmailService reportedEmailService;

    @Resource
    private EmailageRequestBuilder emailageRequestBuilder;

    @Resource
    private EmailageCommService emailageCommService;

    public void sendReportedEmails(Calendar start, Calendar end) {
        reportBuyerEmails(start, end);
        reportDeliveryEmails(start, end);
        reportPayerEmails(start, end);
    }

    private void reportBuyerEmails(Calendar start, Calendar end) {
        logger.info("Calling the sendReportedBuyerEmails method");
        List<ReportedEmail> emails = reportedEmailService.findReportedBuyerEmails(start, end);
        List<String> urls = emailageRequestBuilder.build(emails);
        List<EmailageResponseStatus> statuses = new ArrayList<>();

        for(String url : urls) {
            EmailageResponseStatus status = emailageCommService.sendRequest(url);
            statuses.add(status);
        }

        statuses.parallelStream().forEach(this::logResults);
    }

    private void reportDeliveryEmails(Calendar start, Calendar end) {
        logger.info("Calling the sendReportedDeliveryEmails method");
        List<ReportedEmail> emails = reportedEmailService.findReportedDeliveryEmails(start, end);
        List<String> urls = emailageRequestBuilder.build(emails);
        List<EmailageResponseStatus> statuses = new ArrayList<>();

        for(String url : urls) {
            EmailageResponseStatus status = emailageCommService.sendRequest(url);
            statuses.add(status);
        }

        statuses.parallelStream().forEach(this::logResults);
    }

    private void reportPayerEmails(Calendar start, Calendar end) {
        logger.info("Calling the sendReportedEmails method");
        List<ReportedEmail> emails = reportedEmailService.findReportedPayerEmails(start, end);
        List<String> urls = emailageRequestBuilder.build(emails);
        List<EmailageResponseStatus> statuses = new ArrayList<>();

        for(String url : urls) {
            EmailageResponseStatus status = emailageCommService.sendRequest(url);
            statuses.add(status);
        }

        statuses.parallelStream().forEach(this::logResults);
    }

    private void logResults(EmailageResponseStatus status) {
        logger.info(String.format("Result: status -> '%s', e-mail-> '%s', error code -> '%d', error code description -> '%d'", status.getStatus(), status.getEmail(), status.getErrorCode().getCode(), status.getErrorCode().getDescription()));
    }
}
