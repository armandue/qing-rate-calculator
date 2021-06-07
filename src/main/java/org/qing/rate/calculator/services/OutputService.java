package org.qing.rate.calculator.services;

import org.qing.rate.calculator.exceptions.InvalidQuoteStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OutputService {
    public static void output(final Quote quote) {
        switch (quote.getStatus()) {
            case SUCCESS:
                System.out.printf("Requested amount: £%s %n", quote.getAmount());
                System.out.printf("Annual Interest Rate: %.1f%% %n", quote.getAnnualInterestRate()
                        .multiply(BigDecimal.valueOf(100)));
                System.out.printf("Monthly repayment: £%s %n", quote.getMonthlyRepayment()
                        .setScale(2, RoundingMode.CEILING));
                System.out.printf("Total repayment: £%s %n", quote.getTotalRepayment()
                        .setScale(2, RoundingMode.CEILING));
                break;
            case FAILED:
                System.out.println(quote.getMessage());
                break;
            default:
                throw new InvalidQuoteStatusException();
        }

    }
}
