package ru.fastdelivery.usecase;

import lombok.RequiredArgsConstructor;
import ru.fastdelivery.domain.common.points.DepartureDestinationPropertiesProvider;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Named
@RequiredArgsConstructor
public class TariffCalculateUseCase {

    private final WeightPriceProvider weightPriceProvider;

    private final DepartureDestinationPropertiesProvider depDestProvider;

    private final BigDecimal MINIMAL_DISTANCE = new BigDecimal("450");

    public Price calc(Shipment shipment) {
        var weightAllPackagesKg = shipment.weightAllPackages().kilograms();
        var minimalPrice = weightPriceProvider.minimalPrice();
        Price priceWeight = weightPriceProvider
                .costPerKg()
                .multiply(weightAllPackagesKg)
                .max(minimalPrice);
        Price priceVolume = calcByVolume(shipment);
            if (priceWeight.amount().compareTo(priceVolume.amount()) > 0) {
                return priceWeight;
            }
        return priceVolume;
    }

    public Price calcByVolume(Shipment shipment) {
        var minimalPrice = weightPriceProvider.minimalPrice();
        var volumeAllPackagesMm = shipment.volumeAllPackages().getVolumePack();
        return weightPriceProvider
                .costCubicMeter()
                .multiply(volumeAllPackagesMm)
                .max(minimalPrice);
    }

    public Price calcDistancePrice(Shipment shipment) {
        Price price = calc(shipment);
        var latDeparture = shipment.departure().latitude();
        var longDeparture = shipment.departure().longitude();
        var latDestination = shipment.destination().latitude();
        var longDestination = shipment.destination().longitude();
        checkingLatitudeLongitudeValues(latDeparture,longDeparture,latDestination,longDestination);
        var distance = calculateDistanceMeter(latDeparture.doubleValue(), longDeparture.doubleValue(), latDestination.doubleValue(), longDestination.doubleValue());
        if(distance.compareTo(MINIMAL_DISTANCE) < 0) {
            return roundingPrice(price);
        }
        BigDecimal newPrice = distance.divide(MINIMAL_DISTANCE, 10, RoundingMode.HALF_UP).multiply(price.amount());
        return roundingPrice(new Price(newPrice, shipment.currency()));
    }
    public Price minimalPrice() {
        return weightPriceProvider.minimalPrice();
    }

    public BigDecimal calculateDistanceMeter(double lat1, double long1, double lat2, double long2) {
        return BigDecimal.valueOf(org.apache.lucene.util.SloppyMath
                .haversinMeters(lat1, long1, lat2, long2)).divide(new BigDecimal("1000"),1,RoundingMode.HALF_UP);
    }
    public void checkingLatitudeLongitudeValues (BigDecimal latDeparture, BigDecimal longDeparture, BigDecimal latDestination, BigDecimal longDestination) {

        if(latDeparture.compareTo(depDestProvider.latitudeMin()) < 0 || latDestination.compareTo(depDestProvider.latitudeMin()) < 0) {
            throw  new IllegalArgumentException("The latitude cannot be less than 45");
        }
        if(latDeparture.compareTo(depDestProvider.latitudeMax()) > 0 || latDestination.compareTo(depDestProvider.latitudeMax()) > 0) {
            throw  new IllegalArgumentException("The latitude cannot be more than 65");
        }
        if(longDeparture.compareTo(depDestProvider.longitudeMin()) < 0 || longDestination.compareTo(depDestProvider.longitudeMin()) < 0) {
            throw  new IllegalArgumentException("The longitude cannot be less than 30");
        }
        if(longDeparture.compareTo(depDestProvider.longitudeMax()) > 0 || longDestination.compareTo(depDestProvider.longitudeMax()) > 0) {
            throw  new IllegalArgumentException("The longitude cannot be more than 96");
        }
    }
    public Price roundingPrice(Price price) {
        BigDecimal newPrice = price.amount().divide(new BigDecimal("1"), 2, RoundingMode.CEILING);
        return new Price(newPrice, price.currency());
    }
}
