package com.example.domain.account;

import com.example.domain.user.User;
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
@TableGenerator(name = "ACCOUNT_NUM_GENERATOR")
public class Account {

    @Id
    @GeneratedValue
    private Long accountNum;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private String password;

    private BigDecimal balance;
}
