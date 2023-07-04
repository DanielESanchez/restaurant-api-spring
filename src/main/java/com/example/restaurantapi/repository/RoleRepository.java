package com.example.restaurantapi.repository;

import com.example.restaurantapi.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    @Query(value = "{name:'?0'}")
    Role findRoleByName(String name);
}
