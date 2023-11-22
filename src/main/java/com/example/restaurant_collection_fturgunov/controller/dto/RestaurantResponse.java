package com.example.restaurant_collection_fturgunov.controller.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RestaurantResponse {
    private Integer id;
    private String name;
    private String city;
    private Integer estimatedCost;
    private String averageRating;
    private Integer votes;
}
