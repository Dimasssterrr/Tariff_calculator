package ru.fastdelivery.domain.common.volume.dimensions;

import java.math.BigInteger;

public record Height(BigInteger heightMillimeter) implements Comparable<Height> {


    public Height {

        if(isLessThanZero(heightMillimeter)) {
            throw new IllegalArgumentException("Height cannot be below Zero!");
        }
        heightMillimeter = RoundingUp.roundingIsMultipleFifty(heightMillimeter);

    }
    public Boolean isLessThanZero (BigInteger heightMillimeter) {
        return BigInteger.ZERO.compareTo(heightMillimeter) > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Height height = (Height) o;
        return heightMillimeter.compareTo(height.heightMillimeter) == 0;
    }

    @Override
    public int compareTo(Height h) {
        return h.heightMillimeter().compareTo(heightMillimeter());
    }

    public Boolean greaterThan (Height h) {
        return heightMillimeter.compareTo(h.heightMillimeter()) > 0;
    }

}
