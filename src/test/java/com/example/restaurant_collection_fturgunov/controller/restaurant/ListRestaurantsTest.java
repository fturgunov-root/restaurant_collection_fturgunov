package com.example.restaurant_collection_fturgunov.controller.restaurant;

import com.example.restaurant_collection_fturgunov.CommonIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Return list of restaurants ( GET /restaurant )")
public class ListRestaurantsTest extends CommonIntegrationTest {

    @Test
    @DisplayName("Should list restaurants")
    void shouldListRestaurants() throws Exception {
        //GIVEN
        testDataHelperRestaurant.createRestaurant(payload -> payload.put("name", "name1"));
        testDataHelperRestaurant.createRestaurant(payload -> payload.put("name", "name2"));
        RequestBuilder request = testDataHelperRestaurant.listRestaurants();
                //WHEN
        ResultActions resultActions = mockMvc.perform(request);
        //THEN
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)));
    }

}