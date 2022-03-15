package com.example.utils;

import com.example.constant.UnitCode;
import com.example.dto.ExchangeRateData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class ExchangeRateCalculator {

    private RestTemplate restTemplate = new RestTemplate();

    private final String AUTH_KEY = "SKOpPq41rNSDIy4gZdb1wUo9xC2vDiBI";
    private String url = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=" + AUTH_KEY + "&data=AP01";

    public BigDecimal exchangeUnitForSend(UnitCode unitCode) {
        ExchangeRateData[] result = restTemplate.getForObject(url, ExchangeRateData[].class);
        for (ExchangeRateData unit : result) {
            if (unit.getCur_unit().substring(0, 3).equals(unitCode.name())) {
                return new BigDecimal(unit.getTts().replaceAll(",", ""));
            }
        }
        throw new IllegalArgumentException("COUNTRY_NOT_FOUND");
    }

    public BigDecimal exchangeUnitForGet(UnitCode unitCode) {
        ExchangeRateData[] result = restTemplate.getForObject(url, ExchangeRateData[].class);
        for (ExchangeRateData unit : result) {
            if (unit.getCur_unit().substring(0, 3).equals(unitCode.name())) {
                return new BigDecimal(Long.parseLong(unit.getTtb().replaceAll(",", "")));
            }
        }
        throw new IllegalArgumentException("COUNTRY_NOT_FOUND");
    }
}
