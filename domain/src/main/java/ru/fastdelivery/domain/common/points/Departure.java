package ru.fastdelivery.domain.common.points;

import java.math.BigDecimal;

public record Departure (BigDecimal latitude,
                         BigDecimal longitude
){

    public Departure(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;

    }
}
