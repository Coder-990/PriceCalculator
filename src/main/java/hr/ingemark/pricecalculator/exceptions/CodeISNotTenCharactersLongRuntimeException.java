package hr.ingemark.pricecalculator.exceptions;

import javax.validation.constraints.Size;

public class CodeISNotTenCharactersLongRuntimeException extends RuntimeException {
    public static final String ERROR_MSG = "Code length must be 10 characters long, not ";

    public CodeISNotTenCharactersLongRuntimeException(@Size(min = 10) @Size(max = 10) final String code) {
        super(String.format("%s: %s", ERROR_MSG, code.length()));
    }
}
