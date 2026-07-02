package com.optmap.optmap.controller;

import com.optmap.optmap.dto.Coordinates;
import com.optmap.optmap.dto.NominatimResponseDto;
import com.optmap.optmap.dto.SearchResponseDto;
import com.optmap.optmap.services.NominatimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final NominatimService nominatimService;

    @GetMapping
    public ResponseEntity<List<SearchResponseDto>> getSearchResult(@RequestParam String searchQuery) {
        List<NominatimResponseDto> nominatimResponseDtos = nominatimService.connectWithNominatim(searchQuery);

        return ResponseEntity.ok(mapToSearchResponse(nominatimResponseDtos));

    }

    private List<SearchResponseDto> mapToSearchResponse(List<NominatimResponseDto> nominatimResponseDtos) {
        return nominatimResponseDtos.stream()
                .map(
                        dto -> new SearchResponseDto(dto.getDisplayName(), new Coordinates(dto.getLongitude(), dto.getLatitude()))
                )
                .toList();
    }

}
