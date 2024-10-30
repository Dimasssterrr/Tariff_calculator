package ru.fastdelivery.domain.common.volume.dimensions;

import java.math.BigInteger;

public record Width(BigInteger widthMillimeter) {

    public Width {
        if(isLessThanZero(widthMillimeter)) {
            throw new IllegalArgumentException("Width cannot be below Zero!");
        }
        widthMillimeter = RoundingUp.roundingIsMultipleFifty(widthMillimeter);
    }
    public Boolean isLessThanZero (BigInteger widthMillimeter) {
        return BigInteger.ZERO.compareTo(widthMillimeter) > 0;
    }

    public Boolean greaterThan (Width w) {
        return widthMillimeter.compareTo(w.widthMillimeter()) > 0;
    }
}
