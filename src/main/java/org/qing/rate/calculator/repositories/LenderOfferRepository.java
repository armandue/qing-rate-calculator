package org.qing.rate.calculator.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LenderOfferRepository {

    private final static List<LenderOfferDAO> LENDER_OFFERS =
            Arrays.asList(
                    LenderOfferDAO.builder()
                            .lender("Bob")
                            .annualRate(BigDecimal.valueOf(0.075))
                            .available(640)
                            .build(),
                    LenderOfferDAO.builder()
                            .lender("Jane")
                            .annualRate(BigDecimal.valueOf(0.069))
                            .available(480)
                            .build(),
                    LenderOfferDAO.builder()
                            .lender("Fred")
                            .annualRate(BigDecimal.valueOf(0.071))
                            .available(520)
                            .build(),
                    LenderOfferDAO.builder()
                            .lender("Dave")
                            .annualRate(BigDecimal.valueOf(0.074))
                            .available(140)
                            .build(),
                    LenderOfferDAO.builder()
                            .lender("John")
                            .annualRate(BigDecimal.valueOf(0.081))
                            .available(320)
                            .build(),
                    LenderOfferDAO.builder()
                            .lender("Mary")
                            .annualRate(BigDecimal.valueOf(00.104))
                            .available(170)
                            .build(),
                    LenderOfferDAO.builder()
                            .lender("Angela")
                            .annualRate(BigDecimal.valueOf(00.71))
                            .available(60)
                            .build()
            );

    public static List<LenderOfferDAO> getAll() {
        return new ArrayList<>(LENDER_OFFERS);
    }
}
