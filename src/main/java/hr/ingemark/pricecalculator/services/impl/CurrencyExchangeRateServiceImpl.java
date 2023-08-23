package hr.ingemark.pricecalculator.services.impl;

import hr.ingemark.pricecalculator.exceptions.UnableToFindCurrencyExchangeRateForUsdRuntimeException;
import hr.ingemark.pricecalculator.models.entities.CurrencyExchangeRate;
import hr.ingemark.pricecalculator.services.CurrencyExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

import static hr.ingemark.pricecalculator.util.Constants.HNB_USD_CURRENCY;
import static hr.ingemark.pricecalculator.util.Constants.SAD;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeRateServiceImpl implements CurrencyExchangeRateService {

    private final WebClient.Builder webClientBuilder;

    @Override
    public String getUsdBuyingRate() {
        return Arrays.stream(this.buildWebClient())
                .filter(cER -> cER.getDrzava().equals(SAD))
                .map(CurrencyExchangeRate::getKupovni_tecaj)
                .findFirst()
                .orElseThrow(UnableToFindCurrencyExchangeRateForUsdRuntimeException::new);
    }

    private CurrencyExchangeRate[] buildWebClient() {
        return webClientBuilder.build()
                .get()
                .uri(HNB_USD_CURRENCY)
                .retrieve()
                .bodyToMono(CurrencyExchangeRate[].class)
                .block();
    }



}
