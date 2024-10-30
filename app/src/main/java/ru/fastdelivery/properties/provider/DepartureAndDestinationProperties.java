package ru.fastdelivery.properties.provider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.fastdelivery.domain.common.points.DepartureDestinationPropertiesProvider;

import java.math.BigDecimal;

@ConfigurationProperties("coordinates")
@Getter
@Setter
@AllArgsConstructor
public class DepartureAndDestinationProperties implements DepartureDestinationPropertiesProvider {

   private BigDecimal latitudeMin;

   private BigDecimal latitudeMax;

   private BigDecimal longitudeMin;

   private BigDecimal longitudeMax;

   @Override
   public BigDecimal latitudeMin() {
      return latitudeMin;
   }

   @Override
   public BigDecimal latitudeMax() {
      return latitudeMax;
   }

   @Override
   public BigDecimal longitudeMin() {
      return longitudeMin;
   }

   @Override
   public BigDecimal longitudeMax() {
      return longitudeMax;
   }

}
