package com.dev_incubator.dits.config;

import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

@Component
@Profile("dev-ah")
public class InitData {

    private final DataSource dataSource;

    public InitData(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void populateSampleData() throws SQLException {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        Resource resource = new ClassPathResource("init-data-dev-ah.sql");
        populator.addScript(resource);
        populator.populate(dataSource.getConnection());
    }
}

