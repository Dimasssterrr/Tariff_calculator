package ru.fastdelivery.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.common.points.Departure;
import ru.fastdelivery.domain.common.points.DepartureDestinationPropertiesProvider;
import ru.fastdelivery.domain.common.points.Destination;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
public class CalculateDistanceMeterTest {

    final WeightPriceProvider weightPriceProvider = mock(WeightPriceProvider.class);
    final DepartureDestinationPropertiesProvider depDestPropertyProvider = mock(DepartureDestinationPropertiesProvider.class);

    final TariffCalculateUseCase tariffCalculateUseCase = new TariffCalculateUseCase(weightPriceProvider,depDestPropertyProvider);

    @Test
    @DisplayName("Проверка правильности вычисления дистанции")
    void correctnessDistanceCalculation() {
        Departure departure = new Departure(new BigDecimal("77.1539"), new BigDecimal("120.398"));
        Destination destination = new Destination(new BigDecimal("77.1804"), new BigDecimal("129.55"));
        var expected = tariffCalculateUseCase.calculateDistanceMeter(departure.latitude().doubleValue(), departure.longitude().doubleValue(), destination.latitude().doubleValue(), destination.longitude().doubleValue());
        BigDecimal actual = new BigDecimal("225.8");
        assertEquals(expected, actual);


    }
}
