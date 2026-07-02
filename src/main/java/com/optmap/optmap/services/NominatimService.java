package com.optmap.optmap.services;

import com.optmap.optmap.dto.NominatimResponseDto;
import com.optmap.optmap.errorHandling.InvalidSearchRequestException;
import com.optmap.optmap.errorHandling.NominatimServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NominatimService {

    private final WebClient webClient;

    public List<NominatimResponseDto> connectWithNominatim(String searchQuery) {

        if (searchQuery == null || searchQuery.isEmpty()) {
            throw new InvalidSearchRequestException("Invalid search query");
        }

        try {
            String uri = "http://localhost:7070/search?q=" + searchQuery + "&format=json";

            Mono<List<NominatimResponseDto>> response = webClient.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<NominatimResponseDto>>() {});

            return response.block();
        } catch (WebClientException e) {
            throw new NominatimServiceException("Search service is unavailable");
        }


    }
}
