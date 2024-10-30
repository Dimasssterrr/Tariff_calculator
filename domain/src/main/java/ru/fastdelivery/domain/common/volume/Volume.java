package ru.fastdelivery.domain.common.volume;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.fastdelivery.domain.common.volume.dimensions.Height;
import ru.fastdelivery.domain.common.volume.dimensions.Length;
import ru.fastdelivery.domain.common.volume.dimensions.Width;

import java.math.BigDecimal;
import java.math.RoundingMode;
@Getter
@Setter
@AllArgsConstructor
public class Volume {
    private Length length;
    private Width width;
    private Height height;
    private BigDecimal volumePack;

    public Volume(Length length, Width width, Height height) {
        this.volumePack = new BigDecimal(length.lengthMillimeter())
                .multiply(new BigDecimal(width.widthMillimeter()))
                .multiply(new BigDecimal(height.heightMillimeter()))
                .divide(new BigDecimal("1000000000"),4,RoundingMode.HALF_DOWN);

        if(isLessThanZero(volumePack)) {
            throw new IllegalArgumentException("Volume cannot be below Zero!");
        }
    }

    public Volume(BigDecimal volumePack) {
        this.volumePack = volumePack;
    }

    public Boolean isLessThanZero (BigDecimal volumePack) {
        return BigDecimal.ZERO.compareTo(volumePack) > 0;
    }
    public Volume volumeAdd(Volume additionalVolume) {
        return new Volume(this.volumePack.add(additionalVolume.volumePack));
    }

    public static Volume zero() {
        return new Volume(BigDecimal.ZERO);
    }
}
