package com.example.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InvestmentStatus {

    INVESTING("투자중"),
    CANCELED("투자취소"),
    FINISHED("투자종료")
    ;

    private final String description;
}
