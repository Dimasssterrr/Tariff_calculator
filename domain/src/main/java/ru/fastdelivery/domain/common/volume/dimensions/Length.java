package ru.fastdelivery.domain.common.volume.dimensions;

import java.math.BigInteger;

public record Length(BigInteger lengthMillimeter) {

    public Length {
        if(isLessThanZero(lengthMillimeter)) {
            throw new IllegalArgumentException("Length cannot be below Zero!");
        }
        lengthMillimeter = RoundingUp.roundingIsMultipleFifty(lengthMillimeter);
    }
    public Boolean isLessThanZero (BigInteger lengthMillimeter) {
        return BigInteger.ZERO.compareTo(lengthMillimeter) > 0;
    }
    public Boolean greaterThan (Length l) {
        return lengthMillimeter.compareTo(l.lengthMillimeter()) > 0;
    }
}
