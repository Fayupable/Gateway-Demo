package com.restaurant.restaurant.repository;

import com.restaurant.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant, String> {

    boolean existsById(UUID id);

    Optional<Restaurant> findById(UUID id);

}
