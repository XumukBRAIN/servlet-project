package com.dev.servlet.configs;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class DataSourceConfiguration {

    private static  final Map<String, Object> dataSourceProperties;

    static {
        Yaml yaml = new Yaml();
        InputStream inputStream = DataSourceConfiguration.class.getClassLoader()
                .getResourceAsStream("application.yaml");
        Map<String, Object> data = yaml.load(inputStream);
        dataSourceProperties = (Map<String, Object>) data.get("datasource");
    }

    public static Map<String, Object> getDataSourceProperties() {
        return dataSourceProperties;
    }
}
