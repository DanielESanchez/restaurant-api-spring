package com.example.restaurantapi.repository;

import com.example.restaurantapi.model.Waiter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WaiterRepository extends MongoRepository<Waiter, String> {

    @Query(value = "{idEmployee:'?0'}", fields="{'_id' : 1, idEmployee: 1}")
    Waiter findByIdEmployee(String productId);
}
