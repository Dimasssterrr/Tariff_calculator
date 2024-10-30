package ru.fastdelivery.usecase;

import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.common.currency.Currency;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.points.Departure;
import ru.fastdelivery.domain.common.points.DepartureDestinationPropertiesProvider;
import ru.fastdelivery.domain.common.points.Destination;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.common.volume.dimensions.Height;
import ru.fastdelivery.domain.common.volume.dimensions.Length;
import ru.fastdelivery.domain.common.volume.dimensions.Width;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TariffCalculateUseCaseTest {

    final WeightPriceProvider weightPriceProvider = mock(WeightPriceProvider.class);
    final DepartureDestinationPropertiesProvider depDestPropertyProvider = mock(DepartureDestinationPropertiesProvider.class);
    final Currency currency = new CurrencyFactory(code -> true).create("RUB");

    final TariffCalculateUseCase tariffCalculateUseCase = new TariffCalculateUseCase(weightPriceProvider, depDestPropertyProvider);

    @Test
    @DisplayName("Расчет стоимости доставки -> успешно")
    void whenCalculatePrice_thenSuccess() {
        var minimalPrice = new Price(BigDecimal.TEN, currency);
        var pricePerKg = new Price(BigDecimal.valueOf(100), currency);
        var priceCubicMeter = new Price(BigDecimal.valueOf(100),currency);

        when(weightPriceProvider.minimalPrice()).thenReturn(minimalPrice);
        when(weightPriceProvider.costPerKg()).thenReturn(pricePerKg);
        when(weightPriceProvider.costCubicMeter()).thenReturn(priceCubicMeter);

        var shipment = new Shipment(List.of(new Pack(new Weight(BigInteger.valueOf(1200)), new Length(new BigInteger("10")),new Height(new BigInteger("10")), new Width(new BigInteger("10")))),
                new CurrencyFactory(code -> true).create("RUB"), new Departure(new BigDecimal("64"), new BigDecimal("36")),new Destination(new BigDecimal("45"),new BigDecimal("30")));
        var expectedPrice = new Price(BigDecimal.valueOf(120), currency);

        var actualPrice = tariffCalculateUseCase.calc(shipment);

        assertThat(actualPrice).usingRecursiveComparison()
                .withComparatorForType(BigDecimalComparator.BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expectedPrice);
    }

    @Test
    @DisplayName("Получение минимальной стоимости -> успешно")
    void whenMinimalPrice_thenSuccess() {
        BigDecimal minimalValue = BigDecimal.TEN;
        var minimalPrice = new Price(minimalValue, currency);
        when(weightPriceProvider.minimalPrice()).thenReturn(minimalPrice);

        var actual = tariffCalculateUseCase.minimalPrice();

        assertThat(actual).isEqualTo(minimalPrice);
    }


}