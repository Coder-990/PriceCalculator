package hr.ingemark.pricecalculator.models.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "PRODUCT")
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 10)
    @Size(max = 10)
    @Basic
    @Column(name = "CODE")
    private String code;

    @Basic
    @Column(name = "NAME")
    private String name;

    @Min(value = 0)
    @Basic
    @Column(name = "PRICE_EUR")
    private String priceEur;

    @Min(value = 0)
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
