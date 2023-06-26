package com.example.restaurantapi.repository;

import com.example.restaurantapi.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    @Query(value = "{orderNumber:'?0'}", fields="{'_id' : 1, orderNumber: 1}")
    Order findItemByProductId(long orderNumber);

}