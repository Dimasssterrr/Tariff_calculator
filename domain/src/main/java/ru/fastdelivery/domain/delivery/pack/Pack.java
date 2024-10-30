package ru.fastdelivery.domain.delivery.pack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.fastdelivery.domain.common.volume.dimensions.Height;
import ru.fastdelivery.domain.common.volume.dimensions.Length;
import ru.fastdelivery.domain.common.volume.dimensions.Width;
import ru.fastdelivery.domain.common.weight.Weight;

import java.math.BigInteger;

/**
 * Упаковка груза
 *
 * @param weight вес товаров в упаковке
 */
public record Pack(Weight weight,
                   Length length,
                   Height height,
                   Width width) {

    private static final Weight maxWeight = new Weight(BigInteger.valueOf(150_000));
    private static final Length maxLength = new Length(BigInteger.valueOf(1_500));
    private static final Height maxHeight = new Height(BigInteger.valueOf(1_500));
    private static final Width maxWidth = new Width(BigInteger.valueOf(1_500));

    private static final Logger logger = LogManager.getLogger(Pack.class);

    public Pack {
        if (weight.greaterThan(maxWeight)) {
            logger.error("Package can't be more than " + maxWeight);
            throw new IllegalArgumentException("Package can't be more than " + maxWeight);
        }
        if(length.greaterThan(maxLength)) {
            logger.error("Length can't be more than " + maxLength);
            throw new IllegalArgumentException("Length can't be more than " + maxLength);
        }
        if(height.greaterThan(maxHeight)) {
            logger.error("Height can't be more than" + maxHeight);
            throw new IllegalArgumentException("Height can't be more than " + maxHeight);
        }
        if(width.greaterThan(maxWidth)) {
            logger.error("Width can't be more than " + maxWidth);
            throw new IllegalArgumentException("Width can't be more than " + maxWidth);
        }

    }
}
