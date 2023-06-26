package com.example.restaurantapi.repository;

import com.example.restaurantapi.model.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends MongoRepository<Menu, String> {

    @Query(value = "{productId:'?0'}", fields="{'_id' : 1, productId: 1}")
    Menu findItemByProductId(String productId);

}
