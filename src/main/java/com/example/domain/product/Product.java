package com.example.domain.product;

import com.example.constant.UnitCode;
import com.example.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private BigDecimal totalInvestingAmount;

    private BigDecimal investedAmount;

    private Long investedCount;

    @Enumerated(EnumType.STRING)
    private UnitCode unitCode;

    private LocalDate startAt;

    private LocalDate finishAt;
}
