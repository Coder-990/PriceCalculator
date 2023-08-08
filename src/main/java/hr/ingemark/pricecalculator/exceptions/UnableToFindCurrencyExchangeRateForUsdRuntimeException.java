package hr.ingemark.pricecalculator.exceptions;

public class UnableToFindCurrencyExchangeRateForUsdRuntimeException extends RuntimeException{

    public static final String ERROR_MSG = "Unable to fetch data for USD currency!";

    public UnableToFindCurrencyExchangeRateForUsdRuntimeException() {
        super(ERROR_MSG);
    }
}
