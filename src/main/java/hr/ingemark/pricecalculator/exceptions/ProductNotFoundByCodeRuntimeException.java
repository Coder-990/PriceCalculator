package hr.ingemark.pricecalculator.exceptions;

public class ProductNotFoundByCodeRuntimeException extends RuntimeException{
    public static final String ERROR_MSG = "Could not find product by this code: ";

    public ProductNotFoundByCodeRuntimeException(final String code) {
        super(String.format("%s: %s", ERROR_MSG, code));
    }
}
