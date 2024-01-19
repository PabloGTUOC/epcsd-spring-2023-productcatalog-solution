package edu.uoc.epcsd.productcatalog.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
// Purpose of @TestConfiguration: To define specific configuration for testing, separate from the main application context.
@TestConfiguration
// Purpose of @EnableJpaRepositories: To enable and configure the JPA repositories specifically for testing, pointing to the package with repositories.
@EnableJpaRepositories(basePackages = "edu.uoc.epcsd.productcatalog.domain.repository")
// Purpose of @ComponentScan: To specify which packages the Spring Boot test context should scan for components, configurations, and services.
@ComponentScan(basePackages = {"edu.uoc.epcsd.productcatalog"})
public class TestConfig {
    //Purpose of @Bean:
    @Bean
    @Primary
    public TestEntityManager testEntityManager(EntityManagerFactory entityManagerFactory) {
        return new TestEntityManager(entityManagerFactory);
    }
}
