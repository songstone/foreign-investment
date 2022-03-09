package com.example.domain.investment;

import com.example.constant.InvestmentStatus;
import com.example.domain.BaseTimeEntity;
import com.example.domain.product.Product;
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
public class Investment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private BigDecimal investedAmount;

    @Enumerated(EnumType.STRING)
    private InvestmentStatus status;
}
