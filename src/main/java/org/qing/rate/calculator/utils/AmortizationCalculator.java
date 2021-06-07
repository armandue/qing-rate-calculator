package org.qing.rate.calculator.utils;

import org.qing.rate.calculator.dto.LenderOfferDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class AmortizationCalculator {
    private final static int NUMBER_OF_MONTH = 12;
    private final static int ROUND = 10;
    private final static RoundingMode ROUND_MODE = RoundingMode.CEILING;

    //The monthly payment calculation formula https://en.wikipedia.org/wiki/Amortization_calculator
    public static BigDecimal calculateMonthlyPayment(
            final LenderOfferDTO offer,
            final int years
    ) {
        final BigDecimal monthlyRate = offer.getAnnualRate()
                .divide(
                        BigDecimal.valueOf(NUMBER_OF_MONTH),
                        ROUND,
                        ROUND_MODE
                );
        return monthlyRate.add(
                monthlyRate.divide(
                        BigDecimal.ONE.add(monthlyRate).pow(NUMBER_OF_MONTH * years).subtract(BigDecimal.ONE),
                        ROUND,
                        ROUND_MODE
                )
        ).multiply(BigDecimal.valueOf(offer.getOffer()));
    }

    public static BigDecimal calculateTotalRepayment(
            final BigDecimal monthlyPayment,
            final int years
    ) {
        return monthlyPayment
                .multiply(BigDecimal.valueOf(NUMBER_OF_MONTH))
                .multiply(BigDecimal.valueOf(years));
    }

    public static BigDecimal calculateAnnualInterestRate(
            final List<LenderOfferDTO> offers
    ) {
        final int totalOffers = offers.stream().mapToInt(LenderOfferDTO::getOffer).sum();
        return offers.stream().map(
                offer -> offer.getAnnualRate().multiply(
                            BigDecimal.valueOf((double) offer.getOffer() / totalOffers)
                    )
        ).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}
