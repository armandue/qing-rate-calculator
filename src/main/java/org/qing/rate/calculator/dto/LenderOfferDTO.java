package org.qing.rate.calculator.dto;

import lombok.Builder;
import lombok.Data;
import org.qing.rate.calculator.exceptions.InvalidOfferException;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
public class LenderOfferDTO {
    private final BigDecimal annualRate;
    private final int available;
    private final int offer;

    public static class LenderOfferDTOBuilder {
        private int offer;

        public LenderOfferDTOBuilder offer(final int offer) {
            this.offer = offer;
            validate();
            return this;
        }

        private void validate() {
            if (this.offer > this.available) {
                throw new InvalidOfferException();
            }
        }
    }
}
