package edu.uoc.epcsd.productcatalog.domain.repository;

import edu.uoc.epcsd.productcatalog.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CategoryRepository{

    List<Category> findAllCategories();

    Optional<Category> findCategoryById(Long id);

    List<Category> findCategoriesByExample(Category category);

    Long createCategory(Category category);

}
