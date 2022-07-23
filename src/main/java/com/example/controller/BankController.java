package com.example.controller;

import com.example.domain.account.Account;
import com.example.domain.user.User;
import com.example.dto.Deposit;
import com.example.dto.RegisterAccount;
import com.example.service.BankService;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RequestMapping("/bank")
@RestController
public class BankController {

    private final BankService bankService;
    private final UserService userService;

    @PostMapping("/account")
    public Long registerAccount(@RequestBody RegisterAccount request) {
        User user = userService.getUser(request.getName(), request.getBirth());
        return bankService.registerAccount(user, request.getPassword());
    }

    @GetMapping("/account")
    public Account getAccount(
            @RequestParam Long accountNum,
            @RequestParam String password
    ) {
        return bankService.getAccount(accountNum, password);
    }

    @PostMapping("/account/deposit")
    public Long deposit(@RequestBody Deposit request) {
        Account account = bankService.getAccount(request.getAccountNum(), request.getPassword());
        return bankService.deposit(account, new BigDecimal(request.getMoney()));
    }
}
