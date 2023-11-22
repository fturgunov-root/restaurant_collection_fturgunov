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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Deletes a restaurant ( DELETE /restaurant/{id} )")
class DeleteRestaurantTest extends CommonIntegrationTest {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    @DisplayName("Should delete restaurant by id")
    void shouldListRestaurants() throws Exception {
        //GIVEN
        testDataHelperRestaurant.createRestaurant(payload -> payload.put("name", "name1"));
        RestaurantResponse restaurantResponse = testDataHelperRestaurant.createRestaurant(payload -> payload.put("name", "name2"));
        int restaurantId = restaurantResponse.getId();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(Exception::new);
        assertThat(restaurant.getId()).isEqualTo(restaurantId);

        RequestBuilder request = testDataHelperRestaurant.deleteRestaurant(restaurantId);
        //WHEN
        ResultActions resultActions = mockMvc.perform(request);
        //THEN
        resultActions
                .andExpect(status().isNoContent());
    }

}
