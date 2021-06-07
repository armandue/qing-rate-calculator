package org.qing.rate.calculator.services;

import org.qing.rate.calculator.utils.AmortizationCalculator;
import org.qing.rate.calculator.dto.LenderOfferDTO;
import org.qing.rate.calculator.repositories.LenderOfferDAO;
import org.qing.rate.calculator.repositories.LenderOfferRepository;
import org.qing.rate.calculator.translators.LenderOfferTranslator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LenderOfferService {
    private static final Comparator<LenderOfferDTO> OFFER_COMPARATOR = Comparator.comparing(LenderOfferDTO::getAnnualRate);

    public static Quote calculate(final Request request) {
        final List<LenderOfferDAO> offerDAOs = LenderOfferRepository.getAll();

        final List<LenderOfferDTO> offers = LenderOfferTranslator.convert(offerDAOs);
        final List<LenderOfferDTO> selectedOffers = selectOffers(offers, request.getAmount());

        if (selectedOffers.isEmpty()) {
            return Quote.builder()
                    .status(Quote.QuotaStatus.FAILED)
                    .message("It is not possible to provide a quote.")
                    .build();
        }

        final BigDecimal monthlyRepayment = selectedOffers.stream()
                .map(offer ->
                        AmortizationCalculator.calculateMonthlyPayment(offer, request.getYears())
                )
                .reduce(BigDecimal::add)
                .get();

        final BigDecimal totalRepayment = AmortizationCalculator.calculateTotalRepayment(monthlyRepayment, request.getYears());
        final BigDecimal annualInterestRate = AmortizationCalculator.calculateAnnualInterestRate(selectedOffers);

        return Quote.builder()
                .status(Quote.QuotaStatus.SUCCESS)
                .amount(request.getAmount())
                .monthlyRepayment(monthlyRepayment)
                .totalRepayment(totalRepayment)
                .annualInterestRate(annualInterestRate)
                .build();
    }

    private static List<LenderOfferDTO> selectOffers(
            final List<LenderOfferDTO> offers,
            final int amount
    ) {
        offers.sort(OFFER_COMPARATOR);
        List<LenderOfferDTO> selectedOffers = new ArrayList<>();
        int sum = 0;
        for (LenderOfferDTO offerDTO : offers) {
            if (sum >= amount) {
                break;
            }
            final int offer = Math.min(offerDTO.getAvailable(), amount - sum);
            selectedOffers.add(
                    offerDTO.toBuilder()
                            .offer(offer)
                            .build()
            );
            sum += offer;
        }
        if (amount > sum) {
            return Collections.emptyList();
        }
        return selectedOffers;
    }

}
