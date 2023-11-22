package com.example.restaurant_collection_fturgunov.controller;

import com.example.restaurant_collection_fturgunov.controller.converters.RestaurantConverter;
import com.example.restaurant_collection_fturgunov.controller.dto.RestaurantCreateRequest;
import com.example.restaurant_collection_fturgunov.controller.dto.RestaurantResponse;
import com.example.restaurant_collection_fturgunov.controller.dto.RestaurantUpdateRatingRequest;
import com.example.restaurant_collection_fturgunov.persistence.model.Restaurant;
import com.example.restaurant_collection_fturgunov.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@Tag(name = "Restaurant")
@AllArgsConstructor
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a new restaurant")
    public RestaurantResponse createRestaurant(@Valid @RequestBody RestaurantCreateRequest request) {
        Restaurant restaurant = restaurantService.createRestaurant(RestaurantConverter.convertToEntity(request));
        return RestaurantConverter.convertToResponse(restaurant);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Updates rating of the restaurant")
    public RestaurantResponse updateRestaurantRating(@PathVariable int id,
                                                    @Valid @RequestBody RestaurantUpdateRatingRequest request) {
        Restaurant restaurant = restaurantService.updateRatings(id, request);
        return RestaurantConverter.convertToResponse(restaurant);
    }

    @GetMapping
    @Operation(summary = "Return list of restaurants")
    public List<RestaurantResponse> listRestaurants() {
        List<Restaurant> restaurantList = restaurantService.listAllRestaurants();
        return RestaurantConverter.from(restaurantList);
    }

    @GetMapping("/query")
    @Operation(summary = "Return list of restaurants filtered by city")
    public List<RestaurantResponse> listRestaurantsByCity(@RequestParam String city) {
        List<Restaurant> restaurantList = restaurantService.listRestaurantsByCity(city);
        return RestaurantConverter.from(restaurantList);
    }

    @GetMapping("/get")
    @Operation(summary = "Returns single restaurant by id")
    public RestaurantResponse getRestaurantById(@RequestParam(name = "id") Integer restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantOrThrowBadRequest(restaurantId);
        return RestaurantConverter.convertToResponse(restaurant);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Deletes a restaurant")
    public void deleteRestaurant(@PathVariable Integer id) {
        restaurantService.deleteById(id);
    }

    @GetMapping("/sort")
    @Operation(summary = "Returns the list of all restaurants, sorted by average ratings")
    public List<RestaurantResponse> listRestaurantsSortedByRatings() {
        List<Restaurant> restaurantList = restaurantService.listAllRestorantsOrderByRating();
        return RestaurantConverter.from(restaurantList);
    }
}
