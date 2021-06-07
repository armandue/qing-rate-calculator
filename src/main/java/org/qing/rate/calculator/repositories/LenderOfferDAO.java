package org.qing.rate.calculator.repositories;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true)
public class LenderOfferDAO {
    private final String lender;
    private final BigDecimal annualRate;
    private final int available;
}
