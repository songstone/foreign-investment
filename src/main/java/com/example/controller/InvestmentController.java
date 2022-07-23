package com.example.controller;

import com.example.dto.CancelRequest;
import com.example.dto.InvestmentRequest;
import com.example.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
public class InvestmentController {

    private final InvestmentService investmentService;

    @PostMapping("/investment")
    public Long investment(@RequestBody InvestmentRequest request) {
        return investmentService.investment(request.getAccountNum(), new BigDecimal(request.getMoney()), request.getProductId());
    }

    @DeleteMapping("/investment/{investmentId}")
    public Long cancel(
            @PathVariable Long investmentId,
            @RequestBody CancelRequest request
    ) {
        return investmentService.cancel(investmentId, request.getAccountNum());
    }
}
