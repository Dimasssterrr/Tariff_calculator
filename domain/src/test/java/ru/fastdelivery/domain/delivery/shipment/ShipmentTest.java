package ru.fastdelivery.domain.delivery.shipment;

import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.points.Departure;
import ru.fastdelivery.domain.common.points.Destination;
import ru.fastdelivery.domain.common.volume.dimensions.Height;
import ru.fastdelivery.domain.common.volume.dimensions.Length;
import ru.fastdelivery.domain.common.volume.dimensions.Width;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShipmentTest {

    @Test
    void whenSummarizingWeightOfAllPackages_thenReturnSum() {
        var weight1 = new Weight(BigInteger.TEN);
        var weight2 = new Weight(BigInteger.ONE);
        var length = new Length(BigInteger.valueOf(100));
        var height = new Height(BigInteger.valueOf(100));
        var width = new Width(BigInteger.valueOf(100));

        var packages = List.of(new Pack(weight1,length,height,width), new Pack(weight2,length,height,width));
        var shipment = new Shipment(packages, new CurrencyFactory(code -> true).create("RUB"),new Departure(new BigDecimal("64"), new BigDecimal("36")),new Destination(new BigDecimal("45"), new BigDecimal("30")));

        var massOfShipment = shipment.weightAllPackages();

        assertThat(massOfShipment.weightGrams()).isEqualByComparingTo(BigInteger.valueOf(11));
    }
}