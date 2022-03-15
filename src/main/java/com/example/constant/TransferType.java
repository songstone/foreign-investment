package com.example.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransferType {
    WITHDRAW("출금"),
    DEPOSIT("입금")
    ;

    private final String description;
}
