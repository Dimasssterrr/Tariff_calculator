package ru.fastdelivery.domain.common.points;

import java.math.BigDecimal;

public interface DepartureDestinationPropertiesProvider {

    BigDecimal latitudeMin();
    BigDecimal latitudeMax();
    BigDecimal longitudeMin();
    BigDecimal longitudeMax();

}
