package hr.ingemark.pricecalculator.models.dtos;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private long id;
    private UUID code;
    private String name;
    private String priceEur;
    private String priceUsd;
    private String description;
    private boolean isAvailable;
}
