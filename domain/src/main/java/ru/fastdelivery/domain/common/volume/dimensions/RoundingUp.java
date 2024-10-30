package ru.fastdelivery.domain.common.volume.dimensions;

import java.math.BigInteger;

public class RoundingUp {
    public static BigInteger roundingIsMultipleFifty (BigInteger dimensionMillimeter) {
        BigInteger fifty = BigInteger.valueOf(50);
        BigInteger remainder = dimensionMillimeter.remainder(fifty);
        if(remainder.equals(new BigInteger("0"))) {
            return dimensionMillimeter;
        }
        return dimensionMillimeter.add(fifty.subtract(remainder));
    }
}
