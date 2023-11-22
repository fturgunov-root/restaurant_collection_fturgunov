package com.example.restaurant_collection_fturgunov.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class RestaurantCreateRequest {
    @Size(min = 1, max = 255, message = "The field 'name' should not be empty or exceed 255 symbols in length")
    private String name;

    @Size(max = 255, message = "The field 'city' should not exceed 255 symbols in length")
    private String city;
}