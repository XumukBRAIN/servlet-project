package com.dev.servlet.repositories;

import com.dev.servlet.security.UserInfo;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

import static com.dev.servlet.utils.Constant.CommonConstants.PASSWORD;
import static com.dev.servlet.utils.Constant.CommonConstants.USERNAME;
import static com.dev.servlet.utils.Constant.JDBCFieldsConstants.DRIVER;
import static com.dev.servlet.utils.Constant.JDBCFieldsConstants.URL;

public class SecurityRepository {

    private static final String REGISTER_SCRIPT = "INSERT INTO user_info (username, password) VALUES (?, ?)";
    private final DataSource dataSource;

    public SecurityRepository(Map<String, Object> dataSourceProperties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl((String) dataSourceProperties.get(URL));
        config.setUsername((String) dataSourceProperties.get(USERNAME));
        config.setPassword((String) dataSourceProperties.get(PASSWORD));
        config.setDriverClassName((String) dataSourceProperties.get(DRIVER));

        dataSource = new HikariDataSource(config);
    }

    public void register(UserInfo userInfo) {
        if (userInfo == null) {
            throw new RuntimeException("Не переданы данные для создания ученика");
        }

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(REGISTER_SCRIPT)
        ) {
            statement.setString(1, userInfo.getUsername());
            statement.setString(2, userInfo.getPassword());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
