package org.qing.rate.calculator.services;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request {
    private final int amount;
    private final int years;
}
