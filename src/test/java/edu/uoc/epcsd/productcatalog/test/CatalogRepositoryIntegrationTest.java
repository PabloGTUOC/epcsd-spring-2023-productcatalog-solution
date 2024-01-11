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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = TestConfig.class)
public class CatalogRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CategoryRepository categoryRepository;
    @Test
    void whenFindByName_thenReturnCategory() {
        String categoryName = "Test Category";
        String categoryDescription = "Test Category Description";
        Category category = Category.builder()
                .name(categoryName)
                .description(categoryDescription)
                .build();
        CategoryEntity categoryEntity = CategoryEntity.fromDomain(category);
        entityManager.persistAndFlush(categoryEntity);
        Category fromDb = categoryRepository.findCategoryById(categoryEntity.getId()).get();
        assertThat(fromDb.getName()).isEqualTo(category.getName());
    }
}
