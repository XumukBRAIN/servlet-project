package com.dev.servlet.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.yaml.snakeyaml.Yaml;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Map;

public class DataSourceConfiguration {
    private static final DataSource dataSource;

    static {
        Yaml yaml = new Yaml();
        InputStream inputStream = DataSourceConfiguration.class.getClassLoader()
                .getResourceAsStream("application.yaml");
        Map<String, Object> data = yaml.load(inputStream);
        Map<String, Object> dataSourceProperties = (Map<String, Object>) data.get("datasource");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl((String) dataSourceProperties.get("url"));
        config.setUsername((String) dataSourceProperties.get("username"));
        config.setPassword((String) dataSourceProperties.get("password"));
        config.setDriverClassName((String) dataSourceProperties.get("driver-class-name"));

        dataSource = new HikariDataSource(config);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
