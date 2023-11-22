package com.example.restaurant_collection_fturgunov.controller.restaurant;

import com.example.restaurant_collection_fturgunov.CommonIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Return list of restaurants filtered by city ( ... /restaurant/query?city )")
class GetRestaurantsByCityTest extends CommonIntegrationTest {

    @Test
    @DisplayName("Should list restaurants by city")
    void shouldListRestaurants() throws Exception {
        //GIVEN
        String testCity = "test-city";
        testDataHelperRestaurant.createRestaurant(payload -> {
            payload.put("name", "name1");
            payload.put("city", testCity);
        });
        testDataHelperRestaurant.createRestaurant(payload -> {
            payload.put("name", "name2");
            payload.put("city", testCity);
        });
        testDataHelperRestaurant.createRestaurant(payload -> {
            payload.put("name", "name3");
            payload.put("city", testCity);
        });
        testDataHelperRestaurant.createRestaurant(payload -> {
            payload.put("name", "name4");
            payload.put("city", "other city");
        });

        RequestBuilder request = testDataHelperRestaurant.listRestaurantsByCity(testCity);
        //WHEN
        ResultActions resultActions = mockMvc.perform(request);
        //THEN
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)));
    }
}