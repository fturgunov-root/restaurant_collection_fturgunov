package com.example.restaurant_collection_fturgunov.service;

import com.example.restaurant_collection_fturgunov.controller.dto.RestaurantUpdateRatingRequest;
import com.example.restaurant_collection_fturgunov.persistence.dao.RestaurantRepository;
import com.example.restaurant_collection_fturgunov.persistence.model.Restaurant;
import com.example.restaurant_collection_fturgunov.service.utils.OrderResolver;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final OrderResolver orderResolver;

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant getRestaurantOrThrowBadRequest(int restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(() ->
                new IllegalArgumentException(String.format("Restaurant with id %d does not exist.", restaurantId)));
    }

    @Transactional
    public Restaurant updateRatings(int restaurantId, RestaurantUpdateRatingRequest request) {
        Restaurant restaurant = getRestaurantOrThrowBadRequest(restaurantId);
        restaurant.setAverageRating(String.valueOf(request.getAverageRating()));
        restaurant.setVotes(request.getVotes());
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> listAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public List<Restaurant> listRestaurantsByCity(String city) {
        if (StringUtils.isBlank(city)) {
            throw new IllegalArgumentException("Parameter 'city' should not be blank.");
        }
        return restaurantRepository.findByCity(city);
    }

    public void deleteById(Integer restaurantId) {
        try {
            restaurantRepository.deleteById(restaurantId);
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException(String.format("Restaurant with id %d does not exist.", restaurantId));
        }
    }

    public List<Restaurant> listAllRestorantsOrderByRating() {
        return listAllRestaurants().stream()
                .sorted(orderResolver)
                .toList();
    }
}