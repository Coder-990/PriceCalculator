package hr.ingemark.pricecalculator.models.dtos;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private long id;
    private String code;
    private String name;
    private String priceEur;
    private String priceUsd;
    private String description;
    private Boolean isAvailable;
}
