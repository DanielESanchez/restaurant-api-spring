package com.example.restaurantapi.repository;

import com.example.restaurantapi.model.user.EmployeeUser;
import com.example.restaurantapi.model.user.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{username:'?0'}")
    User findUserByUsername(String username);

    @Query(value = "{username:'?0'}")
    EmployeeUser findEmployeeUserByUsername(String username);

}
