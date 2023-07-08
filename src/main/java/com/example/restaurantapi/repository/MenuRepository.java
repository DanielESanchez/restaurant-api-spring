package com.example.restaurantapi.repository;

import com.example.restaurantapi.model.restaurant.MenuItemRestaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends MongoRepository<MenuItemRestaurant, String> {

    @Query(value = "{productId:'?0'}")
    MenuItemRestaurant findItemByProductId(String productId);


}
