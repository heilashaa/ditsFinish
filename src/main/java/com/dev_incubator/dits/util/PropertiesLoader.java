package com.dev_incubator.dits.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * Utility class for loading property files and getting properties before initializing the application context.
 * <p>
 * Example:
 * <pre>
 *     PropertiesLoader propertiesLoader = new PropertiesLoader("application.properties");
 *     propertiesLoader.getProperty("spring.profiles.active", "prod");
 * </pre>
 * @author  Alexandr Heilash
 */
public class PropertiesLoader {

    private Properties properties;

    public PropertiesLoader(String propertiesFileName) {
        initProperties(propertiesFileName);
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private void initProperties(String propertiesFileName) {
        Resource resources = new ClassPathResource(propertiesFileName);
        Properties prop = new Properties();
        try {
            prop.load(resources.getInputStream());
            setProperties(prop);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String propertyName, String propertyDefaultValue) {
        return getProperties().getProperty(propertyName, propertyDefaultValue);
    }
}
