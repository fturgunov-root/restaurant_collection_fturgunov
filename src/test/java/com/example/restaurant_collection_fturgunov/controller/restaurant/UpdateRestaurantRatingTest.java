package com.example.restaurant_collection_fturgunov.controller.restaurant;

import com.example.restaurant_collection_fturgunov.CommonIntegrationTest;
import com.example.restaurant_collection_fturgunov.controller.dto.RestaurantResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Update the rating of existing restaurant ( PATCH /restaurant/{id} )")
class UpdateRestaurantRatingTest extends CommonIntegrationTest {

    @Test
    @DisplayName("Should update the rating of the restaurant")
    void shouldUpdateRestaurantRating() throws Exception {
        //GIVEN
        RestaurantResponse restaurantResponse = testDataHelperRestaurant.createRestaurant();
        assertThat(restaurantResponse.getAverageRating()).isEqualTo("0");
        assertThat(restaurantResponse.getVotes()).isEqualTo(0);
        int restaurantId = restaurantResponse.getId();

        int testAvgRating = 2;
        int testVotes = 3;
        RequestBuilder request = testDataHelperRestaurant.updateRestaurantRatingRequest(restaurantId, payload -> {
            payload.put("averageRating", testAvgRating);
            payload.put("votes", testVotes);
        });

        //WHEN
        ResultActions resultActions = mockMvc.perform(request);
        //THEN
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(restaurantId))
                .andExpect(jsonPath("$.averageRating").value(testAvgRating))
                .andExpect(jsonPath("$.votes").value(testVotes));
    }
}