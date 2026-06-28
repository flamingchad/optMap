package com.optmap.optmap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripResponseDto {
    private List<Route> trips;
    private List<Waypoint> waypoints;
}
