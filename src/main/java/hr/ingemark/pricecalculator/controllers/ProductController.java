package hr.ingemark.pricecalculator.controllers;

import hr.ingemark.pricecalculator.models.dtos.ProductDto;
import hr.ingemark.pricecalculator.models.entities.Product;
import hr.ingemark.pricecalculator.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hr.ingemark.pricecalculator.util.Constants.*;


@Slf4j
@RestController
@RequestMapping(ProductController.BASE_URL)
@RequiredArgsConstructor
public class ProductController {
    public static final String BASE_URL = "api/v1/products/";

    private final ProductService productService;


    @GetMapping(GET)
    public List<ProductDto> getAll() {
        final List<ProductDto> productDTOS = this.productService.getAllProducts()
                .stream().map(this.productService::convertToDto).toList();
        if (productDTOS.isEmpty()) {
            log.info(EMPTY_LIST);
        } else {
            log.info(PRODUCTS + INITIALIZED_SUCCESSFULLY);
        }
        return productDTOS;
    }

    @GetMapping(GET_BY + ID)
    public ResponseEntity<ProductDto> getById(@PathVariable final Long id) {
        final Product product = this.productService.getOneById(id);
        log.info(PRODUCT + SUCCESSFULLY_BY_ID);
        return this.productService.getProductDTOResponseEntity(product);
    }

    @GetMapping()
    public ResponseEntity<ProductDto> getByCode(@RequestParam(value = CODE) final String code) {
        final Product product = this.productService.getOneByCode(code);
        log.info(PRODUCT + SUCCESSFULLY_BY_CODE);
        return this.productService.getProductDTOResponseEntity(product);
    }

    @PostMapping(CREATE)
    public ResponseEntity<ProductDto> store(@RequestBody final ProductDto personDTO) {
        final Product product = this.productService.createProduct(this.productService.convertToEntity(personDTO));
        log.info(PRODUCT + STORED_PERMANENTLY);
        return this.productService.saveProductDTOResponseEntity(product);
    }

    @PutMapping(UPDATE_BY + ID)
    public ResponseEntity<ProductDto> update(@RequestBody final ProductDto productDto, @PathVariable final Long id) {
        final Product product = this.productService.updateExistingProduct(this.productService.convertToEntity(productDto), id);
        log.info(PRODUCT + UPDATED_SUCCESSFULLY);
        return this.productService.saveProductDTOResponseEntity(product);
    }

    @DeleteMapping(DELETE_BY + ID)
    public HttpStatus delete(@PathVariable final Long id) {
        final HttpStatus status = this.productService.deleteById(id);
        log.info(PRODUCT + DELETED_SUCCESSFULLY);
        return status;
    }
}
