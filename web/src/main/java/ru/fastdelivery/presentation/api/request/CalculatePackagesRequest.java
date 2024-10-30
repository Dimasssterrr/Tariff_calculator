package ru.fastdelivery.presentation.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ru.fastdelivery.domain.common.points.Departure;
import ru.fastdelivery.domain.common.points.Destination;

import java.util.List;

@Schema(description = "Данные для расчета стоимости доставки")
public record CalculatePackagesRequest(
        @Schema(description = "Список упаковок отправления",
                example = "[{\"weight\": 4056.45}]")
        @NotNull
        @NotEmpty
        List<CargoPackage> packages,

        @Schema(description = "Трехбуквенный код валюты", example = "RUB")
        @NotNull
        String currencyCode,
        @Schema(description = "Координаты пункта выдачи", example = "75.677")
        @NotNull
        Departure departure,
        @Schema(description = "Координыты пункта получения", example = "123.67")
        @NotNull
        Destination destination
) {
}
