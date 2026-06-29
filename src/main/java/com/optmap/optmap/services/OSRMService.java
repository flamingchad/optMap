package com.optmap.optmap.services;

import com.optmap.optmap.dto.Coordinates;
import com.optmap.optmap.dto.TripResponseDto;
import com.optmap.optmap.errorHandling.InvalidRouteRequestException;
import com.optmap.optmap.errorHandling.OsrmServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OSRMService {

    private final WebClient webClient;

    public TripResponseDto connectWithOSRM(List<Coordinates> coordinates) {
        if (coordinates.size() < 2) {
            throw new InvalidRouteRequestException("There must be at-least 2 coordinates!!");
        }

        String urlCoordinate = coordinates.stream().map(
                coordinate -> coordinate.longitude() + "," + coordinate.latitude())
                .collect(Collectors.joining(";"));

        String url = "http://localhost:5000/trip/v1/driving/" + urlCoordinate;

        try {
            return webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(TripResponseDto.class)
                    .block();
        } catch (WebClientException e) {
            throw new OsrmServiceException("OSRM routing service is unavailable");
        }
    }

}
