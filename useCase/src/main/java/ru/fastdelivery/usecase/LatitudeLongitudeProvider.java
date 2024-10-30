package ru.fastdelivery.usecase;

import ru.fastdelivery.domain.common.points.Departure;
import ru.fastdelivery.domain.common.points.Destination;

public interface LatitudeLongitudeProvider {
    Destination intervalLatitudeDest();
    Destination intervalLongitudeDest();
    Departure intervalLatitudeDep();
    Departure intervalLongitudeDep();
}
