package com.optmap.optmap.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record Coordinates(

        @NotNull(message = "Longitude is required.")
        @Range(min = -180, max = 180, message = "Longitude must be a valid number between -180 and 180.")
        Double longitude,

        @NotNull(message = "Latitude is required.")
        @Range(min = -90, max = 90, message = "Latitude must be a valid number between -90 and 90.")
        Double latitude
) {
}
