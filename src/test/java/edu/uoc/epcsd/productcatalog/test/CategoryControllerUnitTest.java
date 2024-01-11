package edu.uoc.epcsd.productcatalog.test;

import edu.uoc.epcsd.productcatalog.application.rest.CategoryRESTController;
import edu.uoc.epcsd.productcatalog.domain.Category;
import edu.uoc.epcsd.productcatalog.domain.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerUnitTest {
    @Mock
    private CategoryService categoryService;
    @InjectMocks
    private CategoryRESTController categoryRESTController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryRESTController).build();
    }

    @Test
    void whenAllCatCall_thenAllreturned() throws Exception {
        Category cat1 = new Category();
        cat1.setId(1L);
        cat1.setName("cat1");
        Category cat2 = new Category();
        cat2.setId(2L);
        cat2.setName("cat2");
        List<Category> categories = Arrays.asList(cat1, cat2);
        when(categoryService.findAllCategories()).thenReturn(categories);
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(cat1.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(cat1.getName())))
                .andExpect(jsonPath("$[1].id", is(cat2.getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(cat2.getName())));
    }
}
