package com.example.restaurantapi.controller.role;

import com.example.restaurantapi.model.user.Role;
import com.example.restaurantapi.repository.RoleRepository;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableMongoRepositories
@RequestMapping("api")
public class RoleController {
    private final RoleRepository roleRepository;

    RoleController(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @PostMapping("/role")
    Role newRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }
}
