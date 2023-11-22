package com.example.restaurant_collection_fturgunov.controller.restaurant;

import com.example.restaurant_collection_fturgunov.CommonIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Returns the list of all restaurants, sorted by average ratings ( GET /restaurant/sort )")
class ListRestaurantsSortedByRatingTest extends CommonIntegrationTest {

    @Test
    @DisplayName("Should list restaurants sorted by average rating")
    void shouldListRestaurants() throws Exception {
        //GIVEN
        int id1 = testDataHelperRestaurant.createRestaurant(payload -> payload.put("name", "name1")).getId();
        testDataHelperRestaurant.updateRestaurant(id1, payload -> payload.put("averageRating", 1));

        int id2 = testDataHelperRestaurant.createRestaurant(payload -> payload.put("name", "name2")).getId();
        testDataHelperRestaurant.updateRestaurant(id2, payload -> payload.put("averageRating", 2.2));

        RequestBuilder request = testDataHelperRestaurant.listRestaurantsSortedByRating();
        //WHEN
        ResultActions resultActions = mockMvc.perform(request);
        //THEN

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].averageRating").value(1))
                .andExpect(jsonPath("$.[1].averageRating").value(2.2));
    }
}
