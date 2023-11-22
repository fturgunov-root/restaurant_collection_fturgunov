package com.example.restaurant_collection_fturgunov.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantUpdateRatingRequest {
    @Schema(description = "Positive decimal number, minimal value = 0", defaultValue = "0")
    private BigDecimal averageRating = BigDecimal.valueOf(0);
    @Schema(description = "Positive integer, minimal value = 0", defaultValue = "0")
    @Min(0)
    private Integer votes = 0;
}