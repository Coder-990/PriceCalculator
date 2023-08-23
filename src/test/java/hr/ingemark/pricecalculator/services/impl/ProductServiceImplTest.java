package hr.ingemark.pricecalculator.services.impl;

import hr.ingemark.pricecalculator.MockEntityDataValues;
import hr.ingemark.pricecalculator.exceptions.ProductNotFoundByCodeRuntimeException;
import hr.ingemark.pricecalculator.exceptions.ProductNotFoundByIdRuntimeException;
import hr.ingemark.pricecalculator.models.entities.Product;
import hr.ingemark.pricecalculator.repositories.ProductRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

import static hr.ingemark.pricecalculator.MockEntityDataValues.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @InjectMocks
    private CurrencyExchangeRateServiceImpl currencyExchangeRateService;

    @Mock
    private ProductRepository productRepository;

    private ModelMapper modelMapper;
    private WebClient.Builder webClientBuilder;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(productRepository, currencyExchangeRateService, modelMapper, webClientBuilder);
    }

    @Nested
    @DisplayName("ProductServiceTest get all products")
    class ProductServiceTestAllProducts {
        @Test
        @DisplayName("GIVEN product records exists in database, WHEN all product records are requested, THEN all product from database are returned.")
        void testGetAllProducts() {
            final List<Product> expectedList = MockEntityDataValues.getListOfProductEntities();
            when(productRepository.findAll()).thenReturn(expectedList);
            final List<Product> actualList = productService.getAllProducts();
            assertEquals(actualList, expectedList);
            assertNotNull(actualList);
        }

        @Test
        @DisplayName("GIVEN there are no product records in database, WHEN all product records are requested, THEN empty list is returned.")
        void testGetAllEmpty() {
            final List<Product> expectedList = Collections.emptyList();
            when(productRepository.findAll()).thenReturn(Collections.emptyList());
            final List<Product> actualList = productService.getAllProducts();
            assertEquals(actualList, expectedList);
            assertNotNull(actualList);
        }
    }

    @Nested
    @DisplayName("ProductServiceTest get product by id")
    class ProductServiceTestGetProductById {

        @Test
        @DisplayName("GIVEN product record exists in database, WHEN a single product record is fetched, THEN the product with requested ID is returned.")
        void testGetOneById() {
            final Product expectedProduct = MockEntityDataValues.getListOfProductEntities().get(0);
            when(productRepository.findById(ID_1))
                    .thenReturn(MockEntityDataValues.getListOfProductEntities().stream()
                            .filter(product -> product.getId() == ID_1)
                            .findFirst());
            final Product actualProduct = productService.getOneById(ID_1);
            assertNotNull(actualProduct);
            assertEquals(expectedProduct, actualProduct);
        }

        @Test
        @DisplayName("GIVEN product record does not exists in database, WHEN a single product record is fetched, THEN error is thrown.")
        void testGetOneByNonExistingId() {
            when(productRepository.findById(NON_EXISTING_ID)).thenThrow(new ProductNotFoundByIdRuntimeException(NON_EXISTING_ID));
            assertThrows(ProductNotFoundByIdRuntimeException.class, () -> productService.getOneById(NON_EXISTING_ID));
        }
    }

    @Nested
    @DisplayName("ProductServiceTest get product by code")
    class ProductServiceTestGetProductByCode {

        @Test
        @DisplayName("GIVEN product record exists in database, WHEN a single product record is fetched, THEN the product with requested CODE is returned.")
        void testGetOneByCode() {
            final Product expectedProduct = MockEntityDataValues.getListOfProductEntities().get(1);
            when(productRepository.findByCode(PRODUCT_CODE_2))
                    .thenReturn(MockEntityDataValues.getListOfProductEntities().stream()
                            .filter(product -> product.getCode().equals(PRODUCT_CODE_2))
                            .findFirst());
            final Product actualProduct = productService.getOneByCode(PRODUCT_CODE_2);
            assertNotNull(actualProduct);
            assertEquals(expectedProduct, actualProduct);
        }

        @Test
        @DisplayName("GIVEN product record does not exists in database, WHEN a single product record is fetched, THEN exception is thrown.")
        void testGetOneByNonExistingCode() {
            String nonExistingProductCode = PRODUCT_CODE_2.replace("5", "A");
            when(productRepository.findByCode(nonExistingProductCode)).thenThrow(new ProductNotFoundByCodeRuntimeException(nonExistingProductCode));
            assertThrows(ProductNotFoundByCodeRuntimeException.class, () -> productService.getOneByCode(nonExistingProductCode));
        }
    }


    @Nested
    @DisplayName("ProductServiceTest create product")
    class ProductServiceTestCreateProduct {

        @Test
        @DisplayName("GIVEN product record does not exist in database, WHEN new product record is created, THEN new record is created and returned.")
        void testCreateProduct() {
            final Product expectedProduct = MockEntityDataValues.getListOfProductEntities().get(0);
            when(productRepository.save(any(Product.class))).thenReturn(expectedProduct);
            when(productRepository.existsAllByCodeAndIdIsNotLike(expectedProduct)).thenReturn(Collections.emptyList());
            final Product actualProduct = productService.createProduct(expectedProduct);
            assertNotNull(actualProduct);
            assertEquals(expectedProduct, actualProduct);
        }
    }

    @Nested
    @DisplayName("ProductServiceTest delete product by id")
    class ProductServiceTestDeleteProductById {
        @Test
        @DisplayName("GIVEN product record either exist or not, WHEN a single product record is deleted, THEN repository delete method should be called once.")
        void testDeleteById() {
            final Long productId = MockEntityDataValues.getListOfProductEntities().get(0).getId();
            productRepository.deleteProductById(productId);
            verify(productRepository, times(1)).deleteProductById(productId);
        }
    }
}