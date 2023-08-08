package hr.ingemark.pricecalculator.models.dtos;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyExchangeRateDto {

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
