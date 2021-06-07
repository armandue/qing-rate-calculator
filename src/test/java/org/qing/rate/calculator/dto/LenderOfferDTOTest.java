package org.qing.rate.calculator.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.qing.rate.calculator.exceptions.InvalidOfferException;

import java.math.BigDecimal;

public class LenderOfferDTOTest {

    @Test
    public void test_builder_happy() {
        final LenderOfferDTO offer = LenderOfferDTO.builder()
                .annualRate(BigDecimal.valueOf(0.1))
                .available(1000)
                .offer(1000)
                .build();

        Assertions.assertEquals(offer.getOffer(), 1000);
        Assertions.assertEquals(offer.getAvailable(), 1000);
        Assertions.assertEquals(offer.getAnnualRate(), BigDecimal.valueOf(0.1));
    }

    @Test
    public void test_builder_offer_bigger_than_available() {
        Assertions.assertThrows(
                InvalidOfferException.class,
                () ->
                    LenderOfferDTO.builder()
                            .annualRate(BigDecimal.valueOf(0.1))
                            .available(100)
                            .offer(1000)
                            .build()

        );
    }
}
