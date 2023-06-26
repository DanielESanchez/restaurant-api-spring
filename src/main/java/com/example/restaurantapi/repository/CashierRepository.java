package com.example.restaurantapi.repository;

import com.example.restaurantapi.model.Cashier;
import com.example.restaurantapi.model.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CashierRepository extends MongoRepository<Cashier, String> {

    @Query(value = "{idEmployee:'?0'}", fields="{'_id' : 1, idEmployee: 1}")
    Cashier findByIdEmployee(String productId);
}
