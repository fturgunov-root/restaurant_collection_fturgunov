package com.example.restaurant_collection_fturgunov.controller.converters;

import com.example.restaurant_collection_fturgunov.controller.dto.RestaurantCreateRequest;
import com.example.restaurant_collection_fturgunov.controller.dto.RestaurantResponse;
import com.example.restaurant_collection_fturgunov.persistence.model.Restaurant;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class RestaurantConverter {
    public static Restaurant convertToEntity(RestaurantCreateRequest request) {
        return Restaurant.builder()
                .name(request.getName())
                .city(request.getCity())
                .build();
    }

    public static RestaurantResponse convertToResponse(Restaurant restaurant) {
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .city(restaurant.getCity())
                .estimatedCost(restaurant.getEstimatedCost())
                .averageRating(restaurant.getAverageRating())
                .votes(restaurant.getVotes())
                .build();
    }

    public static List<RestaurantResponse> from(List<Restaurant> restaurantList) {
        return restaurantList.stream()
                .map(RestaurantConverter::convertToResponse)
                .toList();
    }
}