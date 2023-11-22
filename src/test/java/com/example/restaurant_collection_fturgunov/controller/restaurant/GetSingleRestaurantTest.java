package com.example.restaurant_collection_fturgunov.controller.restaurant;

import com.example.restaurant_collection_fturgunov.CommonIntegrationTest;
import com.example.restaurant_collection_fturgunov.controller.dto.RestaurantResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Returns single restaurant by id ( ... /restaurant/get?id )")
class GetSingleRestaurantTest extends CommonIntegrationTest {

    @Test
    @DisplayName("Should get a correct restaurant by id")
    void shouldGetARestaurants() throws Exception {
        //GIVEN
        testDataHelperRestaurant.createRestaurant(payload -> payload.put("name", "name1"));
        String nameTest = "name-test";
        RestaurantResponse restaurantResponse = testDataHelperRestaurant.createRestaurant(payload -> payload.put("name", nameTest));
        int restaurantId = restaurantResponse.getId();
        RequestBuilder request = testDataHelperRestaurant.getRestaurantByIdRequest(restaurantId);
        //WHEN
        ResultActions resultActions = mockMvc.perform(request);
        //THEN
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(restaurantId))
                .andExpect(jsonPath("$.name").value(nameTest));
    }
}