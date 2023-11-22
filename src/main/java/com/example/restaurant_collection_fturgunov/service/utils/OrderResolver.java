package com.example.restaurant_collection_fturgunov.service.utils;

import com.example.restaurant_collection_fturgunov.persistence.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;

@Component
@RequiredArgsConstructor
public class OrderResolver implements Comparator<Restaurant> {
    @Override
    public int compare(Restaurant o1, Restaurant o2) {
        String rating1 = o1.getAverageRating();
        String rating2 = o2.getAverageRating();

        // Handle null or empty strings
        if (rating1 == null || rating1.isEmpty()) {
            return -1;
        }
        if (rating2 == null || rating2.isEmpty()) {
            return 1;
        }

        // Parse the ratings as decimal numbers
        BigDecimal value1 = new BigDecimal(rating1);
        BigDecimal value2 = new BigDecimal(rating2);

        // Compare the ratings
        return value1.compareTo(value2);
    }
}