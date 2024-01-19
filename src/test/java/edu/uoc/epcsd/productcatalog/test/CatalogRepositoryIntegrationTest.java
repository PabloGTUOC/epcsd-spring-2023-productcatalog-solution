package edu.uoc.epcsd.productcatalog.test;
import edu.uoc.epcsd.productcatalog.config.TestConfig;
import edu.uoc.epcsd.productcatalog.domain.Category;
import edu.uoc.epcsd.productcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.productcatalog.infrastructure.repository.jpa.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
//Purpose @DataJpaTest is used to configure an in-memory database for testing the JPA layer.
@DataJpaTest
//Purpose @AutoConfigureTestDatabase is used to configure the database to use for the tests.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//Purpose @ContextConfiguration is used to load the Spring configuration.
@ContextConfiguration(classes = TestConfig.class)
public class CatalogRepositoryIntegrationTest {
    //Purpose @Autowired is used to inject the TestEntityManager.
    @Autowired
    private TestEntityManager entityManager;
    //Purpose @Autowired is used to inject the CategoryRepository.
    @Autowired
    private CategoryRepository categoryRepository;
    //Purpose of the test is to verify that the findCategoryById() method of the CategoryRepository class returns the category with the given id.
    @Test
    void whenFindByName_thenReturnCategory() {
        String categoryName = "Test Category";
        String categoryDescription = "Test Category Description";
        Category category = Category.builder()
                .name(categoryName)
                .description(categoryDescription)
                .build();
        CategoryEntity categoryEntity = CategoryEntity.fromDomain(category);
        //Purpose The persistAndFlush() method is used to save the categoryEntity object to the database.
        entityManager.persistAndFlush(categoryEntity);
        //Purpose The findCategoryById() method is used to retrieve the categoryEntity object from the database.
        Category fromDb = categoryRepository.findCategoryById(categoryEntity.getId()).get();
        //Purpose The assertThat() method is used to verify that the categoryEntity object retrieved from the database is the same as the one saved.
        assertThat(fromDb.getName()).isEqualTo(category.getName());
    }
}
