package org.qing.rate.calculator.services;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
public class Quote {
    private final int amount;
    private final BigDecimal annualInterestRate;
    private final BigDecimal monthlyRepayment;
    private final BigDecimal totalRepayment;
    private final QuotaStatus status;
    private final String message;

    public enum QuotaStatus {
        SUCCESS, FAILED
    }
}
