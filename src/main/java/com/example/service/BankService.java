package com.example.service;

import com.example.domain.account.Account;
import com.example.domain.account.AccountRepository;
import com.example.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class BankService {

    private final AccountRepository accountRepository;

    @Transactional
    public Long registerAccount(User user, String password) {
        Account account = Account.builder()
                .user(user)
                .password(password)
                .balance(new BigDecimal(0))
                .build();
        return accountRepository.save(account).getAccountNum();
    }

    public Account getAccount(Long accountNum, String password) {
        Account account = accountRepository.findByAccountNumAndPassword(accountNum, password).orElseThrow(
                () -> new IllegalArgumentException("NOT_INVALID_ACCOUNT")
        );
        return account;
    }

    @Transactional
    public Long deposit(Account account ,BigDecimal money) {
        return account.deposit(money);
    }
}
