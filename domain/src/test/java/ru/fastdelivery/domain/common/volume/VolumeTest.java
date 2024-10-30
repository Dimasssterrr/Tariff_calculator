package ru.fastdelivery.domain.common.volume;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.common.volume.dimensions.Height;
import ru.fastdelivery.domain.common.volume.dimensions.Length;
import ru.fastdelivery.domain.common.volume.dimensions.Width;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class VolumeTest {
    @Test
    @DisplayName("Попытка создать отрицательный объем -> Исключение")
    void volumeTestBelowZero_thenException() {
        var length = new BigInteger("-23");
        var width = new BigInteger("1234");
        var height = new BigInteger("456");
        assertThatThrownBy(() -> new Volume(new Length(length),new Width(width),new Height(height)))
                .isInstanceOf(IllegalArgumentException.class);
    }
   @Test
   @DisplayName("Правильность расчета объема")
    void correctVolumeValue() {
        var length = new BigInteger("23");
        var width = new BigInteger("1234");
        var height = new BigInteger("456");
        Volume volume = new Volume(new Length(length), new Width(width), new Height(height));
        BigDecimal actual = new BigDecimal("0.0312");
        assertEquals(volume.getVolumePack(), actual);
    }
    @Test
    @DisplayName("Правильность добавления объема")
    void correctVolumeAdd() {
       Volume volumeOne = new Volume(new Length(new BigInteger("100")), new Width(new BigInteger("100")),new Height(new BigInteger("100")));

       Volume volumeTwo = new Volume(new Length(new BigInteger("100")), new Width(new BigInteger("100")),new Height(new BigInteger("100")));
        System.out.println(volumeOne.getVolumePack() + " - " + volumeTwo.getVolumePack());

        BigDecimal volumeAllPackages = volumeOne.volumeAdd(volumeTwo).getVolumePack();
        BigDecimal actual = new BigDecimal("0.0020");
        assertEquals(volumeAllPackages, actual);

    }
}
