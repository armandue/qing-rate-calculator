package org.qing.rate.calculator.services;

import org.qing.rate.calculator.exceptions.InvalidRequestException;

public class RequestGenerator {

    public static Request input(final String... args) {
        final int amount;
        final int years;
        try {
            amount = Integer.parseInt(args[0]);
            years = Integer.parseInt(args[1]);
        } catch (RuntimeException e) {
            throw new InvalidRequestException();
        }

        if (amount <= 0 || years <= 0) {
            throw new InvalidRequestException();
        }

        return Request.builder()
                .amount(amount)
                .years(years)
                .build();
    }
}
