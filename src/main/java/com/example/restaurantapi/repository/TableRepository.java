package com.example.restaurantapi.repository;

import com.example.restaurantapi.model.restaurant.Table;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends MongoRepository<Table, String> {

    @Query(value = "{tableNumber:?0}")
    Table findByTableNumber(Long tableNumber);

}