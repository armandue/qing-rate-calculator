package org.qing.rate.calculator.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.qing.rate.calculator.dto.LenderOfferDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AmortizationCalculatorTest {

    @Test
    public void test_calculateMonthlyPayment_happy() {
        final LenderOfferDTO offer = LenderOfferDTO.builder()
                .annualRate(BigDecimal.valueOf(0.1))
                .available(1000)
                .offer(1000)
                .build();
        BigDecimal result = AmortizationCalculator.calculateMonthlyPayment(offer, 3);

        Assertions.assertEquals(
                BigDecimal.valueOf(32.27),
                result.setScale(2, BigDecimal.ROUND_CEILING)
        );
    }

    @Test
    public void test_calculateMonthlyPayment_zero_offer() {
        final LenderOfferDTO offer = LenderOfferDTO.builder()
                .annualRate(BigDecimal.valueOf(0.1))
                .available(1000)
                .offer(0)
                .build();
        BigDecimal result = AmortizationCalculator.calculateMonthlyPayment(offer, 3);

        Assertions.assertEquals(
                BigDecimal.ZERO,
                result.setScale(0, BigDecimal.ROUND_CEILING)
        );
    }

    @Test
    public void test_calculateAnnualInterestRate() {
        final List<LenderOfferDTO> offers = Arrays.asList(
                LenderOfferDTO.builder()
                        .annualRate(BigDecimal.valueOf(0.1))
                        .available(1000)
                        .offer(1000)
                        .build(),
                LenderOfferDTO.builder()
                        .annualRate(BigDecimal.valueOf(0.2))
                        .available(1000)
                        .offer(1000)
                        .build(),
                LenderOfferDTO.builder()
                        .annualRate(BigDecimal.valueOf(0.3))
                        .available(1000)
                        .offer(1000)
                        .build()
        );
        BigDecimal result = AmortizationCalculator.calculateAnnualInterestRate(offers);

        Assertions.assertEquals(
                BigDecimal.valueOf(0.2),
                result.setScale(1, BigDecimal.ROUND_CEILING)
        );
    }

    @Test
    public void test_calculateAnnualInterestRate_empty() {
        BigDecimal result = AmortizationCalculator.calculateAnnualInterestRate(Collections.emptyList());
        Assertions.assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void test_calculateTotalRepayment() {
        final BigDecimal monthlyRepayment = BigDecimal.ONE;
        BigDecimal result = AmortizationCalculator.calculateTotalRepayment(monthlyRepayment, 3);

        Assertions.assertEquals(BigDecimal.valueOf(36), result);
    }
}
