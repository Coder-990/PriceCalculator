package hr.ingemark.pricecalculator.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CurrencyExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    public String broj_tecajnice;
    public String datum_primjene;
    public String drzava;
    public String drzava_iso;
    public String sifra_valute;
    public String valuta;
    public String kupovni_tecaj;
    public String srednji_tecaj;
    public String prodajni_tecaj;

}
