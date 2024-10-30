package ru.fastdelivery.presentation.api.response;

import lombok.extern.slf4j.Slf4j;
import ru.fastdelivery.domain.common.price.Price;

import java.math.BigDecimal;
@Slf4j
public record CalculatePackagesResponse(
        BigDecimal totalPrice,
        BigDecimal minimalPrice,
        String currencyCode
) {
    public CalculatePackagesResponse(Price totalPrice, Price minimalPrice) {
        this(totalPrice.amount(), minimalPrice.amount(), totalPrice.currency().getCode());
        if (currencyIsNotEqual(totalPrice, minimalPrice)) {
            log.error("Currency codes must be the same");
            throw new IllegalArgumentException("Currency codes must be the same");
        }
    }

    private static boolean currencyIsNotEqual(Price priceLeft, Price priceRight) {
        return !priceLeft.currency().equals(priceRight.currency());
    }
}
