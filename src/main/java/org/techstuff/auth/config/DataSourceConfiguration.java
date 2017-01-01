package org.techstuff.auth.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;

@Configuration
@ConfigurationProperties(locations = "classpath:mysql.properties", prefix="mysql")
public class DataSourceConfiguration {
    private static final Integer DEFAULT_INITIAL_POOL_SIZE = 10;
    private static final Integer DEFAULT_MAX_ACTIVE_SIZE = 10;

    @NotNull
    private String userName;

    @NotNull
    private String password;

    @NotNull
    private String dbUrl;

    @NotNull
    private String driverClassName;

    @NotNull
    private Integer initialPoolSize;

    @NotNull
    private Integer maxActive;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setInitialPoolSize(String size) {
        try {
            initialPoolSize = Integer.parseInt(size);
        } catch (NumberFormatException exception) {
            initialPoolSize = DEFAULT_INITIAL_POOL_SIZE;
        }
    }

    public void setMaxActive(String size) {
        try {
            maxActive = Integer.parseInt(size);
        } catch (NumberFormatException exception) {
            maxActive = DEFAULT_MAX_ACTIVE_SIZE;
        }
    }

    @Bean
    public javax.sql.DataSource dataSource() throws SQLException {
        DataSource ds = new DataSource();
        ds.setUsername(userName);
        ds.setPassword(password);
        ds.setUrl(dbUrl);
        ds.setDriverClassName(driverClassName);
        ds.setTestOnConnect(true);
        ds.setInitialSize(initialPoolSize);
        ds.setMaxActive(maxActive);
        ds.setMinIdle(10);
        ds.setMaxIdle(20);
        ds.setMaxWait(10000);
        ds.setValidationQuery("SELECT 1");
        ds.setDefaultAutoCommit(false);
        return ds;
    }
}
