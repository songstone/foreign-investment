package com.example.service;

import com.example.domain.account.Account;
import com.example.domain.account.AccountRepository;
import com.example.domain.investment.Investment;
import com.example.domain.investment.InvestmentRepository;
import com.example.domain.product.Product;
import com.example.domain.product.ProductRepository;
import com.example.domain.user.User;
import com.example.utils.ExchangeRateCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.example.constant.InvestmentStatus.INVESTING;
import static com.example.constant.UnitCode.KRW;

@RequiredArgsConstructor
@Service
public class InvestmentService {

    private final ExchangeRateCalculator calculator;
    private final InvestmentRepository investmentRepository;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;

    public List<Investment> getInvestmentList(){
        return investmentRepository.findAll();
    }

    public List<Investment> getInvestmentList(User user) {
        return investmentRepository.findAllByUser(user);
    }

    @Transactional
    public Long investment(Long accountNum, BigDecimal money, Long productId) {

        Account account = accountRepository.findById(accountNum).orElseThrow(
                () -> new IllegalArgumentException("ACCOUNT_NOT_FOUND")
        );
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("PRODUCT_NOT_FOUND")
        );

        account.withdraw(money);
        if (!product.getUnitCode().equals(KRW)) {
            BigDecimal exchangeRate = calculator.exchangeUnitForSend(product.getUnitCode());
            money = money.divide(exchangeRate, 2, RoundingMode.HALF_EVEN);
        }
        Investment investment = Investment.builder()
                .user(account.getUser())
                .product(product)
                .investedAmount(money)
                .unitCode(product.getUnitCode())
                .status(INVESTING)
                .build();
        product.updateProductInvestment(money);

        return investmentRepository.save(investment).getId();
    }

    @Transactional
    public Long cancel(Long investmentId, Long accountNum) {
        Investment investment = investmentRepository.findById(investmentId).orElseThrow(
                () -> new IllegalArgumentException("INVESTMENT_NOT_FOUND")
        );
        Account account = accountRepository.findById(accountNum).orElseThrow(
                () -> new IllegalArgumentException("ACCOUNT_NOT_FOUND")
        );
        Product product = investment.getProduct();
        BigDecimal money = investment.getInvestedAmount();
        product.updateProductCancel(money);

        if (!product.getUnitCode().equals(KRW)) {
            BigDecimal exchangeRate = calculator.exchangeUnitForGet(product.getUnitCode());
            money = money.divide(exchangeRate, 2, RoundingMode.HALF_EVEN);
        }
        account.deposit(money);
        investment.cancel();
        return investment.getId();
    }
}
