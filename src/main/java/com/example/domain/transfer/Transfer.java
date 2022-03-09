package com.example.domain.transfer;

import com.example.constant.TransferType;
import com.example.domain.BaseTimeEntity;
import com.example.domain.account.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transfer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @Enumerated(EnumType.STRING)
    private TransferType type;

    private BigDecimal money;

    private String encounter;
}
