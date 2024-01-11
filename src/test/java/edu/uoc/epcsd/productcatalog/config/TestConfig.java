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

@TestConfiguration
@EnableJpaRepositories(basePackages = "edu.uoc.epcsd.productcatalog.domain.repository")
@ComponentScan(basePackages = {"edu.uoc.epcsd.productcatalog"}) // Adjust this to include packages that contain your components/services
public class TestConfig {

    @Bean
    @Primary
    public TestEntityManager testEntityManager(EntityManagerFactory entityManagerFactory) {
        return new TestEntityManager(entityManagerFactory);
    }
}
