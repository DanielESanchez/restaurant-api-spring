package com.example.restaurantapi.repository;

import com.example.restaurantapi.model.person.Chef;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChefRepository extends MongoRepository<Chef, String> {

    @Query(value = "{idEmployee:'?0'}")
    Chef findByIdEmployee(String idEmployee);

    @Query(value = "{job:'chef'}")
    List<Chef> findAllChefs();

}
