package com.optmap.optmap.controller;

import com.optmap.optmap.dto.*;
import com.optmap.optmap.services.OSRMService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/route")
@RequiredArgsConstructor
public class ApiController {

    private final OSRMService osrmService;

    @PostMapping
    public ResponseEntity<RouteResponseDto> postRequest(@RequestBody RouteRequestDto routeRequestDto) {
        TripResponseDto tripResponseDto = osrmService.connectWithOSRM(routeRequestDto.getRouteWayPoints());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapperFunc(tripResponseDto));
    }

    private RouteResponseDto mapperFunc(TripResponseDto tripResponseDto) {
        List<Route> routes = tripResponseDto.getTrips();
        List<Waypoint> waypoints = tripResponseDto.getWaypoints();

        Double distance = routes.getFirst().getDistance();
        Double duration = routes.getFirst().getDuration();

        List<Coordinates> coordinates = waypoints.stream().sorted(Comparator.comparingInt(Waypoint::getWaypointIndex)).map(
                waypoint -> {
                    double longitude = waypoint.getLocation().get(0);
                    double latitude = waypoint.getLocation().get(1);
                    return new Coordinates(longitude, latitude);
                }).toList();

        return new RouteResponseDto(distance, duration, coordinates);
    }
}
