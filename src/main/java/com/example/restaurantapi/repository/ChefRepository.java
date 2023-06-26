package com.example.restaurantapi.repository;

import com.example.restaurantapi.model.Chef;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepository extends MongoRepository<Chef, String> {

    @Query(value = "{idEmployee:'?0'}", fields="{'_id' : 1, idEmployee: 1}")
    Chef findByIdEmployee(String idEmployee);

}
