package com.example.restaurantapi.repository;

import com.example.restaurantapi.model.person.Cashier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashierRepository extends MongoRepository<Cashier, String> {

    @Query(value = "{idEmployee:'?0'}")
    Cashier findByIdEmployee(String productId);

    @Query(value = "{job:'cashier'}")
    List<Cashier> findAllCashiers();
}
