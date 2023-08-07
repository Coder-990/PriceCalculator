package hr.ingemark.pricecalculator.services.impl;

import hr.ingemark.pricecalculator.models.dtos.ProductDto;
import hr.ingemark.pricecalculator.models.entities.Product;
import hr.ingemark.pricecalculator.repositories.ProductRepository;
import hr.ingemark.pricecalculator.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(final Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateExistingProduct(final Product productValue, final Long id) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setCode(productValue.getCode());
                    existingProduct.setName(productValue.getName());
                    existingProduct.setPriceEur(productValue.getPriceEur());
                    existingProduct.setPriceUsd(productValue.getPriceUsd());
                    existingProduct.setCode(productValue.getCode());
                    existingProduct.setDescription(productValue.getDescription());
                    existingProduct.setAvailable(productValue.isAvailable());
                    return productRepository.saveAndFlush(existingProduct);
                }).orElseThrow(null);
    }

    @Override
    public HttpStatus deleteById(final Long id) {
        final HttpStatus httpStatus;
        if (this.productRepository.deleteProductById(id) > 0) {
            httpStatus = HttpStatus.NO_CONTENT;
        } else {
            httpStatus = HttpStatus.UNAUTHORIZED;
        }
        return httpStatus;
    }

    @Override
    public ResponseEntity<ProductDto> getPersonDTOResponseEntity(final Product product) {
        final ResponseEntity<ProductDto> responseEntity;
        if (product != null) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.convertToDto(product));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<ProductDto> savePersonDTOResponseEntity(final Product product) {
        final ResponseEntity<ProductDto> responseEntity;
        if (product != null) {
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(this.convertToDto(product));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return responseEntity;
    }

    @Override
    public Product convertToEntity(final ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }

    @Override
    public ProductDto convertToDto(final Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

}
