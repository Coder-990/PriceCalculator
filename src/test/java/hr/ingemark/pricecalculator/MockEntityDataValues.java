package hr.ingemark.pricecalculator;

import hr.ingemark.pricecalculator.models.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public class MockEntityDataValues {
    public static final long ID_1 = 1L;
    public static final long NON_EXISTING_ID = 999L;
    public static final String PRODUCT_CODE_1 = "4145eRfd3H";
    public static final String PRODUCT_NAME = "Product";
    public static final String PRODUCT_CODE_2 = "5726GDFsH4";
    public static final String PRODUCT_DESCRIPTION = "This is product number";
    public static final BigDecimal PRICE_EUR = new BigDecimal("12.29");
    public static final BigDecimal PRICE_USD = new BigDecimal("0.0");

    public static List<Product> getListOfProductEntities() {
        return List.of(getProduct1(), getProduct2());
    }

    private static Product getProduct1() {
        return new Product(1L, PRODUCT_CODE_1, PRODUCT_NAME.concat("1"), PRICE_EUR,
                PRICE_USD, PRODUCT_DESCRIPTION.concat("1"), isAvailable());
    }

    private static Product getProduct2() {
        return new Product(2L, PRODUCT_CODE_2, PRODUCT_NAME.concat("2"), PRICE_EUR,
                PRICE_USD, PRODUCT_DESCRIPTION.concat("2"), isAvailable());
    }

    private static boolean isAvailable() {
        return true;
    }
}
