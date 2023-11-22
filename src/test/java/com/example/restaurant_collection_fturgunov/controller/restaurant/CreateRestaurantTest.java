package com.example.restaurant_collection_fturgunov.controller.restaurant;

import com.example.restaurant_collection_fturgunov.CommonIntegrationTest;
import com.example.restaurant_collection_fturgunov.controller.dto.RestaurantResponse;
import com.example.restaurant_collection_fturgunov.persistence.dao.RestaurantRepository;
import com.example.restaurant_collection_fturgunov.persistence.model.Restaurant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static com.example.restaurant_collection_fturgunov.TestDataHelperRestaurant.TEST_CITY;
import static com.example.restaurant_collection_fturgunov.TestDataHelperRestaurant.TEST_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("Create a new restaurant ( POST /restaurant )")
class CreateRestaurantTest extends CommonIntegrationTest {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    @DisplayName("Should create a restaurant")
    void shouldCreateRestaurant() throws Exception {
        //GIVEN
        RequestBuilder request = testDataHelperRestaurant.createRestaurantRequest();
        //WHEN
        ResultActions resultActions = mockMvc.perform(request);
        //THEN
        String jsonResponse = resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(TEST_NAME))
                .andExpect(jsonPath("$.city").value(TEST_CITY))
                .andExpect(jsonPath("$.estimatedCost").value(0))
                .andExpect(jsonPath("$.averageRating").value("0"))
                .andExpect(jsonPath("$.votes").value(0))
                .andReturn().getResponse().getContentAsString();

        RestaurantResponse restaurantResponse = jsonConverter.convertFromString(jsonResponse, RestaurantResponse.class);
        Restaurant restaurant = restaurantRepository.findById(restaurantResponse.getId()).orElseThrow(Exception::new);
        assertThat(restaurant.getName()).isEqualTo(TEST_NAME);
        assertThat(restaurant.getCity()).isEqualTo(TEST_CITY);
    }
}