package ru.fastdelivery.domain.common.price;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.fastdelivery.domain.common.currency.Currency;
import ru.fastdelivery.domain.common.volume.Volume;

import java.math.BigDecimal;

/**
 * @param amount   значение цены
 * @param currency валюта цены
 */
public record Price(
        BigDecimal amount,
        Currency currency) {

    private static final Logger logger = LogManager.getLogger(Price.class);
    public Price {
        if (isLessThanZero(amount)) {
            logger.error("Price Amount cannot be below Zero!");
            throw new IllegalArgumentException("Price Amount cannot be below Zero!");
        }
    }

    private static boolean isLessThanZero(BigDecimal price) {
        return BigDecimal.ZERO.compareTo(price) > 0;
    }

    public Price multiply(BigDecimal amount) {
        return new Price(this.amount.multiply(amount), this.currency);
    }

    public Price max(Price price) {
        if (!currency.equals(price.currency)) {
            logger.error("Cannot compare Prices in difference Currency!");
            throw new IllegalArgumentException("Cannot compare Prices in difference Currency!");
        }
        return new Price(this.amount.max(price.amount), this.currency);
    }
}
