package org.soaring.emailage.data;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository
public class ReportedEmailDAO  {
    private static final Logger logger = Logger.getLogger(ReportedEmailDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<ReportedEmail> findReportedBuyerEmailsByAnalysisDate(Calendar startDate, Calendar endDate) {
        logger.info("Calling findReportedEmailsByAnalysisDate method");

        String query = "SELECT dec.trans_id, la.data, dec.email, m.descricao, m.idMotivo FROM [DenunciaEmailComprador] dec WITH(NOLOCK) INNER JOIN [trans] t with(nolock) ON dec.trans_id = t.trans_id INNER JOIN [loganalise] la with(nolock) ON dec.trans_id = la.trans_id INNER JOIN [motivo] m with(nolock) ON la.idMotivo = m.idMotivo WHERE la.data BETWEEN ? AND ? AND la.idStatus in(6, 10)";
        List<ReportedEmail> results = jdbcTemplate.query(query, new Object[] {startDate, endDate}, new ReportedEmailRowMapper());
        return results;
    }

    @Transactional(readOnly = true)
    public List<ReportedEmail> findReportedDeliveryEmailsByAnalysisDate(Calendar startDate, Calendar endDate) {
        logger.info("Calling findReportedEmailsByAnalysisDate method");

        String query = "SELECT dec.trans_id, la.data, dec.email, m.descricao, m.idMotivo FROM [DenunciaEmailEntrega] dec WITH(NOLOCK) INNER JOIN [trans] t with(nolock) ON dec.trans_id = t.trans_id INNER JOIN [loganalise] la with(nolock) ON dec.trans_id = la.trans_id INNER JOIN [motivo] m with(nolock) ON la.idMotivo = m.idMotivo WHERE la.data BETWEEN ? AND ? AND la.idStatus in(6, 10)";
        List<ReportedEmail> results = jdbcTemplate.query(query, new Object[] {startDate, endDate}, new ReportedEmailRowMapper());
        return results;
    }

    @Transactional(readOnly = true)
    public List<ReportedEmail> findReportedPayerEmailsByAnalysisDate(Calendar startDate, Calendar endDate) {
        logger.info("Calling findReportedEmailsByAnalysisDate method");

        String query = "SELECT dec.trans_id, la.data, dec.email, m.descricao, m.idMotivo FROM [DenunciaEmailPagador] dec WITH(NOLOCK) INNER JOIN [trans] t with(nolock) ON dec.trans_id = t.trans_id INNER JOIN [loganalise] la with(nolock) ON dec.trans_id = la.trans_id INNER JOIN [motivo] m with(nolock) ON la.idMotivo = m.idMotivo WHERE la.data BETWEEN ? AND ? AND la.idStatus in(6, 10)";
        List<ReportedEmail> results = jdbcTemplate.query(query, new Object[] {startDate, endDate}, new ReportedEmailRowMapper());
        return results;
    }
}
