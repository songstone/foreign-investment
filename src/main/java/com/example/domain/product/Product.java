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

    public Long updateProductInvestment(BigDecimal money) {
        checkTotalInvestingAmount(money);
        this.investedAmount = this.investedAmount.add(money);
        this.investedCount = this.investedCount + 1;
        return this.id;
    }

    public Long updateProductCancel(BigDecimal money) {
        this.investedAmount = this.investedAmount.subtract(money);
        this.investedCount = this.investedCount - 1;
        return this.id;
    }

    private boolean checkTotalInvestingAmount(BigDecimal money) {
        if(getTotalInvestingAmount().subtract(getInvestedAmount()).compareTo(money) >= 0) {
            return true;
        }
        else {
            throw new IllegalArgumentException("NOT_ENOUGH_AMOUNT");
        }
    }
}
