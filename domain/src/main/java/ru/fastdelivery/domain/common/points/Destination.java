package ru.fastdelivery.domain.common.points;

import java.math.BigDecimal;


public record Destination (
        BigDecimal latitude,
        BigDecimal longitude
){

    public Destination(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
