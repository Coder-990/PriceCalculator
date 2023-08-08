package hr.ingemark.pricecalculator.exceptions;

import hr.ingemark.pricecalculator.models.entities.Product;

public class ProductExistsByCodeRuntimeException extends RuntimeException {
    public static final String ERROR_MSG = "Product by this code exists: ";

    public ProductExistsByCodeRuntimeException(final Product product) {
        super(String.format("%s: %s", ERROR_MSG, product.getCode()));
    }
}
