package org.qing.rate.calculator.translators;

import org.qing.rate.calculator.dto.LenderOfferDTO;
import org.qing.rate.calculator.repositories.LenderOfferDAO;

import java.util.List;
import java.util.stream.Collectors;

public class LenderOfferTranslator {

    public static List<LenderOfferDTO> convert(final List<LenderOfferDAO> daoList) {
        return daoList.stream()
                .map(LenderOfferTranslator::convert)
                .collect(Collectors.toList());
    }

    public static LenderOfferDTO convert(final LenderOfferDAO dao) {
        return LenderOfferDTO.builder()
                .annualRate(dao.getAnnualRate())
                .available(dao.getAvailable())
                .build();
    }
}
