package com.optmap.optmap.services;

import com.optmap.optmap.dto.Coordinates;
import com.optmap.optmap.dto.TripResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OSRMService {

    private final WebClient webClient;

    public TripResponseDto connectWithOSRM(List<Coordinates> coordinates) {
        String urlCoordinate = coordinates.stream().map(
                coordinate -> coordinate.longitude() + "," + coordinate.latitude())
                .collect(Collectors.joining(";"));

        String url = "http://localhost:5000/trip/v1/driving/" + urlCoordinate;

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(TripResponseDto.class)
                .block();
    }

}
