package com.optmap.optmap.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RouteRequestDto {
    @Builder.Default
    @Valid
    @Size(min = 2, max = 50, message = "A route request must contain between 2 and 50 waypoints.")
    private List<Coordinates> routeWayPoints = new ArrayList<>();
}
