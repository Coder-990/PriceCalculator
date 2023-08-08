package hr.ingemark.pricecalculator.services;

import hr.ingemark.pricecalculator.models.dtos.ProductDto;
import hr.ingemark.pricecalculator.models.entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getOneById(Long id);

    Product getOneByCode(String code);

    Product createProduct(Product product);

    Product updateExistingProduct(Product productValue, Long id);

    HttpStatus deleteById(Long id);

    ResponseEntity<ProductDto> getProductDTOResponseEntity(Product person);

    ResponseEntity<ProductDto> saveProductDTOResponseEntity(Product person);

    Product convertToEntity(ProductDto productDto);

    ProductDto convertToDto(Product product);
}
