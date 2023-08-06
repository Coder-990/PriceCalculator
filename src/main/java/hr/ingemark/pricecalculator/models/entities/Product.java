package hr.ingemark.pricecalculator.models.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "PRODUCT")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 10)
    @Size(min = 10)
    @Basic
    @Column(name = "CODE")
    private UUID code;

    @Basic
    @Column(name = "NAME")
    private String name;

    @Size(min = 0)
    @Basic
    @Column(name = "PRICE_EUR")
    private String priceEur;

    @Size(min = 0)
    @Basic
    @Column(name = "PRICE_USD")
    private String priceUsd;

    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    @Basic
    @Column(name = "IS_AVAILABLE")
    private boolean isAvailable;
}
