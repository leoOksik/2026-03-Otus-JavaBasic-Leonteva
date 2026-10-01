package ru.otus.server.database.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DataSourceConfig {

    private static final String DB_URL = System.getenv().getOrDefault(
            "DB_URL", "jdbc:postgresql://localhost:5433/test_db");

    private static final String DB_USER = System.getenv().getOrDefault(
            "TEST_DB_USER", "test_user");

    private static final String DB_PASSWORD = System.getenv().getOrDefault(
            "TEST_DB_PASSWORD", "test_pass");

    private static final int POOL_SIZE = Integer.parseInt(
            System.getenv().getOrDefault("DB_POOL_SIZE", "15"));

    private static final long CONNECTION_TIMEOUT_MS = 5_000;

    private static final String POOL_NAME = "db_pool";

    private DataSourceConfig() {
    }

    public static HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PASSWORD);
        config.setMaximumPoolSize(POOL_SIZE);
        config.setMinimumIdle(POOL_SIZE);
        config.setConnectionTimeout(CONNECTION_TIMEOUT_MS);
        config.setPoolName(POOL_NAME);

        log.info("DB pool -> {}", POOL_NAME);
        return new HikariDataSource(config);
    }
}
