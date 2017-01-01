package org.techstuff.auth.data;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by andersonkmi on 1/1/2017.
 */
public class RestrictedWordRowMapper implements RowMapper<RestrictedWord> {
    private static final Logger logger = Logger.getLogger(RestrictedWordRowMapper.class);

    @Override
    public RestrictedWord mapRow(ResultSet resultSet, int i) throws SQLException {
        logger.info(String.format("Processing row number = %d", i));

        RestrictedWord restrictedWord = new RestrictedWord();
        restrictedWord.setId(resultSet.getInt("id"));
        restrictedWord.setWord(resultSet.getString("word"));
        return restrictedWord;
    }
}
