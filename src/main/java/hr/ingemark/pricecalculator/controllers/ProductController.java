package hr.ingemark.pricecalculator.controllers;

import hr.ingemark.pricecalculator.models.dtos.ProductDto;
import hr.ingemark.pricecalculator.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static hr.ingemark.pricecalculator.util.AppConstants.*;


@Slf4j
@RestController
@RequestMapping(ProductController.BASE_URL)
@RequiredArgsConstructor
public class ProductController {

    public static String USD_TO_EUR = "https://api.hnb.hr/tecajn-eur/v3?valuta=USD";

    private final ProductService productService;

    public static final String BASE_URL = "api/v1/product/";
    public static final String GET_ALL = "getAll";

    @GetMapping(GET_ALL)
    public List<ProductDto> getAll() {
        final List<ProductDto> productDtos = this.productService.getAllProducts()
                .stream().map(this.productService::convertToDto).toList();
        if (productDtos.isEmpty()) {
            log.info(EMPTY_LIST);
        } else {
            log.info(PRODUCTS + INITIALIZED_SUCCESSFULLY);
        }
        return productDtos;
    }
}
