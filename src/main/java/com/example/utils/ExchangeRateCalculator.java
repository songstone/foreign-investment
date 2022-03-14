package com.example.utils;

import com.example.constant.UnitCode;
import com.example.dto.ExchangeRateData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class ExchangeRateCalculator {

    private RestTemplate restTemplate = new RestTemplate();

    private final String AUTH_KEY = "SKOpPq41rNSDIy4gZdb1wUo9xC2vDiBI";
    private String url = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=" + AUTH_KEY + "&data=AP01";

    public String exchangeUnitForSend(UnitCode unitCode) {
        ExchangeRateData[] result = restTemplate.getForObject(url, ExchangeRateData[].class);
        for (ExchangeRateData unit : result) {
            if (unit.getCur_unit().substring(0,3).equals(unitCode.name())) {
                return unit.getTts();
            }
        }
        throw new IllegalArgumentException("COUNTRY_NOT_FOUND");
    }

    public String exchangeUnitForGet(UnitCode unitCode) {
        ExchangeRateData[] result = restTemplate.getForObject(url, ExchangeRateData[].class);
        for (ExchangeRateData unit : result) {
            if (unit.getCur_unit().substring(0,3).equals(unitCode.name())) {
                return unit.getTtb();
            }
        }
        throw new IllegalArgumentException("COUNTRY_NOT_FOUND");
    }
}
