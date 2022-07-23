package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentRequest {
    private Long accountNum;
    private Long money;
    private Long productId;
}
