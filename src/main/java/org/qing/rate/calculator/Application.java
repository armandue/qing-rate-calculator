package org.qing.rate.calculator;


import org.qing.rate.calculator.services.OutputService;
import org.qing.rate.calculator.services.RequestGenerator;
import org.qing.rate.calculator.services.LenderOfferService;
import org.qing.rate.calculator.services.Quote;
import org.qing.rate.calculator.services.Request;

public class Application {

    public static void main(String... args) {
        final Request request = RequestGenerator.input(args);
        final Quote quote = LenderOfferService.calculate(request);
        OutputService.output(quote);
    }
}
