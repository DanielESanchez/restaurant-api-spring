package com.example.restaurantapi.repository;

import com.example.restaurantapi.model.person.Waiter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaiterRepository extends MongoRepository<Waiter, String> {

    @Query(value = "{idEmployee:'?0'}")
    Waiter findByIdEmployee(String productId);

    @Query(value = "{job:'waiter'}")
    List<Waiter> findAllWaiter();
}
