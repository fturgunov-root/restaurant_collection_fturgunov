package com.example.restaurant_collection_fturgunov.persistence.dao;

import com.example.restaurant_collection_fturgunov.persistence.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> findByCity(String city);
}