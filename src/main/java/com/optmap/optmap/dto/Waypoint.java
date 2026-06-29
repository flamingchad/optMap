package com.optmap.optmap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Waypoint {
    private String name;
    @JsonProperty("waypoint_index")
    private Integer waypointIndex;
    private List<Double> location;
}
