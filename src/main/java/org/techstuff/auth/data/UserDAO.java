package org.techstuff.auth.data;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDAO {
    private static final Logger logger = Logger.getLogger(UserDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void add(final User user) {
        logger.info("Calling add method");

        String statement = "INSERT INTO user(user_name, fist_name, last_name) VALUES(?, ?, ?)";
        int modified = jdbcTemplate.update(statement, new Object[] {user.getUserName(), user.getFirstName(), user.getLastName()});
        logger.info(String.format("Inserted/Modified rows: %d", modified));
    }

    @Transactional(readOnly = true)
    public int count(final String userName) {
        logger.info("Calling exists method");

        String statement = "SELECT COUNT(1) FROM user WHERE user_name = ?";
        return jdbcTemplate.queryForObject(statement, new Object[] {userName}, Integer.class);
    }
}
