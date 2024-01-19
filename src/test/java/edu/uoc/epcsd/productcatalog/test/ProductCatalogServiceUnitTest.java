package edu.uoc.epcsd.productcatalog.test;

import edu.uoc.epcsd.productcatalog.domain.Product;
import edu.uoc.epcsd.productcatalog.domain.repository.ProductRepository;
import edu.uoc.epcsd.productcatalog.domain.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
//Purpose of  @ExtendWith is to tell JUnit to enable the Mockito extension.
@ExtendWith(MockitoExtension.class)
public class ProductCatalogServiceUnitTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCatalogServiceUnitTest.class);
    //Purpose of @Mock is to tell Mockito to create a mock object for the ProductRepository class.
    @Mock
    private ProductRepository productRepository;
    //Purpose of @InjectMocks is to tell Mockito to inject the mocks marked with @Mock to this instance when it is created.
    @InjectMocks
    private ProductServiceImpl productService;
    private final Long validId = 1L;
    private final Long invalidId = -1L;
    private Product mockProduct;
    //Purpose of @BeforeEach is to tell JUnit to execute this method before each test.
    @BeforeEach
    public void setUp() {
        mockProduct = new Product();
        mockProduct.setId(validId);
        mockProduct.setName("Product 1");
        mockProduct.setDescription("Description 1");
        mockProduct.setDailyPrice(10.0);
        LOGGER.info("Set Up completed");
    }
    @Test
    public void whenValidId_thenProductShouldBeFound() {
        LOGGER.info("Start Test ValidID");
        when(productRepository.findProductById(validId)).thenReturn(Optional.of(mockProduct));
        Optional<Product> foundProduct = productService.findProductById(validId);
        assertThat(foundProduct.isPresent()).isTrue();
        assertThat(foundProduct.get().getId()).isEqualTo(validId);
        LOGGER.info("Find product by id completed");
    }
    @Test
    public void whenInvalidId_thenProductShouldNotBeFound() {
        LOGGER.info("Start Test Non-ValidID");
        when(productRepository.findProductById(invalidId)).thenReturn(Optional.empty());
        Optional<Product> foundProduct = productService.findProductById(invalidId);
        assertThat(foundProduct.isPresent()).isFalse();
        LOGGER.info("Find product by non valid id completed");
    }
}