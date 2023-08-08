package hr.ingemark.pricecalculator.services.impl;

import hr.ingemark.pricecalculator.exceptions.CodeISNotTenCharactersLongRuntimeException;
import hr.ingemark.pricecalculator.exceptions.ProductExistsByCodeRuntimeException;
import hr.ingemark.pricecalculator.exceptions.ProductNotFoundByCodeRuntimeException;
import hr.ingemark.pricecalculator.exceptions.ProductNotFoundByIdRuntimeException;
import hr.ingemark.pricecalculator.models.dtos.ProductDto;
import hr.ingemark.pricecalculator.models.entities.Product;
import hr.ingemark.pricecalculator.repositories.ProductRepository;
import hr.ingemark.pricecalculator.services.CurrencyExchangeRateService;
import hr.ingemark.pricecalculator.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CurrencyExchangeRateService currencyExchangeRateService;

    private final ModelMapper modelMapper;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getOneById(final Long id) {
        Optional<Product> product = this.productRepository.findById(id);
        return product.orElseThrow(() -> new ProductNotFoundByIdRuntimeException(id));
    }

    @Override
    public Product getOneByCode(final String code) {
        Optional<Product> product = this.productRepository.findByCode(code);
        return product.orElseThrow(() -> new ProductNotFoundByCodeRuntimeException(code));
    }

    @Override
    public Product createProduct(final Product product) {
        return this.saveProduct(product);
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
                    existingProduct.setIsAvailable(productValue.getIsAvailable());
                    return this.createProduct(existingProduct);
                }).orElseThrow(() -> new ProductExistsByCodeRuntimeException(productValue));
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
    public ResponseEntity<ProductDto> getProductDTOResponseEntity(final Product product) {
        final ResponseEntity<ProductDto> responseEntity;
        if (product != null) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.convertToDto(product));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<ProductDto> saveProductDTOResponseEntity(final Product product) {
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

    private Product saveProduct(final Product product) {
        if (!productRepository.existsAllByCodeAndIdIsNotLike(product).isEmpty()) {
            throw new ProductExistsByCodeRuntimeException(product);
        }
        return this.productRepository.save(this.buildProductWithUsdCurrency(product));
    }

    private Product buildProductWithUsdCurrency(final Product product) {
        return Product.builder()
                .id(product.getId())
                .code(this.getCodeWithTenCharacters(product))
                .name(product.getName())
                .priceEur(this.getPriceInEurBiggerThanZero(product.getPriceEur()))
                .priceUsd(this.multiplyEuroByUsdCurrency(this.getPriceInEurBiggerThanZero(product.getPriceEur())))
                .description(product.getDescription())
                .isAvailable(product.getIsAvailable())
                .build();
    }

    private BigDecimal getPriceInEurBiggerThanZero(final BigDecimal price) {
        return Objects.requireNonNullElseGet(price, () -> new BigDecimal(0));
    }

    private String getCodeWithTenCharacters(final Product product) {
        if (product.getCode().length() != 10) {
            throw new CodeISNotTenCharactersLongRuntimeException(product.getCode());
        } else {
            return product.getCode();
        }
    }

    private BigDecimal multiplyEuroByUsdCurrency(final BigDecimal priceEur) {
        return priceEur.multiply(new BigDecimal(currencyExchangeRateService
                .getUsdBuyingRate()
                .replace(",", ".")));
    }


}
