package hr.ingemark.pricecalculator.exceptions;

public class ProductNotFoundByIdRuntimeException extends RuntimeException {
    public static final String ERROR_MSG = "Could not find person by this id: ";

    public ProductNotFoundByIdRuntimeException(final Long id) {
        super(String.format("%s: %d", ERROR_MSG, id));
    }
}
