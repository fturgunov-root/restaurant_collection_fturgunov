package com.example.restaurant_collection_fturgunov;

import com.example.restaurant_collection_fturgunov.common.JsonConverter;
import com.example.restaurant_collection_fturgunov.controller.dto.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
@Profile("test")
@RequiredArgsConstructor
public class TestDataHelperRestaurant {
    public static final String TEST_NAME = "test_name";
    public static final String TEST_CITY = "test_city";

    private static final String BASE_PATH = "/restaurant";
    private static final String SINGLE_RECORD_PATH = BASE_PATH + "/{id}";
    private static final String GET_SINGLE_BY_ID_PATH = BASE_PATH + "/get?id={id}";
    private static final String LIST_BY_CITY_PATH = BASE_PATH + "/query?city={city}";
    private static final String LIST_SORTED_BY_RATING_PATH = BASE_PATH + "/sort";

    private final JsonConverter jsonConverter;
    private final MockMvc mockMvc;

    public RequestBuilder getRestaurantByIdRequest(Integer id) {
        return get(GET_SINGLE_BY_ID_PATH, id);
    }

    public RequestBuilder deleteRestaurant(Integer id) {
        return delete(SINGLE_RECORD_PATH, id);
    }

    public RequestBuilder listRestaurants() {
        return get(BASE_PATH);
    }

    public RequestBuilder listRestaurantsSortedByRating() {
        return get(LIST_SORTED_BY_RATING_PATH);
    }

    public RequestBuilder listRestaurantsByCity(String city) {
        return get(LIST_BY_CITY_PATH, city);
    }

    public RequestBuilder createRestaurantRequest(@Nullable Consumer<Map<String, Object>> payloadModifier) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", TEST_NAME);
        payload.put("city", TEST_CITY);
        if (payloadModifier != null) {
            payloadModifier.accept(payload);
        }
        return post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.convertToString(payload));
    }

    public RequestBuilder createRestaurantRequest() {
        return createRestaurantRequest(null);
    }

    @SneakyThrows
    public RestaurantResponse createRestaurant(@Nullable Consumer<Map<String, Object>> payloadModifier) {
        RequestBuilder request = createRestaurantRequest(payloadModifier);
        String jsonResponse = mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();
        return jsonConverter.convertFromString(jsonResponse, RestaurantResponse.class);
    }

    @SneakyThrows
    public RestaurantResponse updateRestaurant(Integer id, Consumer<Map<String, Object>> payloadModifier) {
        RequestBuilder request = updateRestaurantRatingRequest(id, payloadModifier);
        String jsonResponse = mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();
        return jsonConverter.convertFromString(jsonResponse, RestaurantResponse.class);
    }

    public RestaurantResponse createRestaurant() {
        return createRestaurant(null);
    }

    public RequestBuilder updateRestaurantRatingRequest(Integer restaurantId, @Nullable Consumer<Map<String, Object>> payloadModifier) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("averageRating", 0);
        payload.put("votes", 0);
        if (payloadModifier != null) {
            payloadModifier.accept(payload);
        }
        return patch(SINGLE_RECORD_PATH, restaurantId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.convertToString(payload));
    }
}
