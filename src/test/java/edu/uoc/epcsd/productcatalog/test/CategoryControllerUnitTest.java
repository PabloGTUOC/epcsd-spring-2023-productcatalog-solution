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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
//Purpose @ExtendWith(MockitoExtension) is used to integrate Mockito with JUnit 5.
@ExtendWith(MockitoExtension.class)
public class CategoryControllerUnitTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryControllerUnitTest.class);
    //Purpose @Mock creates a mock implementation for the CategoryService class.
    @Mock
    private CategoryService categoryService;
    //Purpose @InjectMocks creates an instance of the CategoryRESTController class and injects the mocks that are created with the @Mock annotation into this instance.
    @InjectMocks
    private CategoryRESTController categoryRESTController;
    //Purpose MockMvc is the main entry point for server-side Spring MVC test support. It allows us to execute requests against the test context.
    private MockMvc mockMvc;
    //Purpose @BeforeEach is used to signal that the annotated method should be executed before each @Test method in the current test class.
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryRESTController).build();
        LOGGER.info("Set Up completed");
    }
    //Purpose of this test is to verify that the findAllCategories() method of the CategoryService class is called when the /categories endpoint is called.
    @Test
    void whenAllCatCall_thenAllreturned() throws Exception {
        Category cat1 = new Category();
        cat1.setId(1L);
        cat1.setName("cat1");
        Category cat2 = new Category();
        cat2.setId(2L);
        cat2.setName("cat2");
        List<Category> categories = Arrays.asList(cat1, cat2);
        //Purpose when() is used to configure the mock object to return the list of categories when the findAllCategories() method is called.
        when(categoryService.findAllCategories()).thenReturn(categories);
        //Purpose mockMvc.perform() is used to perform a GET request to the /categories endpoint.
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                //Purpose jsonPath() is used to verify that the first element of the array has the id and name attributes with the values of the cat1 object.
                .andExpect(jsonPath("$[0].id", is(cat1.getId().intValue())))
                //Purpose jsonPath() is used to verify that the first element of the array has the id and name attributes with the values of the cat1 object.
                .andExpect(jsonPath("$[0].name", is(cat1.getName())))
                //Purpose jsonPath() is used to verify that the second element of the array has the id and name attributes with the values of the cat2 object.
                .andExpect(jsonPath("$[1].id", is(cat2.getId().intValue())))
                //Purpose jsonPath() is used to verify that the second element of the array has the id and name attributes with the values of the cat2 object.
                .andExpect(jsonPath("$[1].name", is(cat2.getName())));
        LOGGER.info("Test is completed");
    }
}
