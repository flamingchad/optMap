package com.optmap.optmap.dto;

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
    List<Coordinates> routeWayPoints = new ArrayList<>();
}
