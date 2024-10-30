package ru.fastdelivery.domain.common.currency;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Создание валюты с проверками
 */
@RequiredArgsConstructor
public class CurrencyFactory {

    private final CurrencyPropertiesProvider propertiesProvider;

    private static final Logger logger = LogManager.getLogger(CurrencyFactory.class);

    public Currency create(String code) {
        if (code == null || !propertiesProvider.isAvailable(code)) {
            logger.error("Currency code contains not available value");
            throw new IllegalArgumentException("Currency code contains not available value");
        }

        return new Currency(code);
    }
}
