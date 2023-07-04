package com.example.restaurantapi.controller.role;

import com.example.restaurantapi.model.Role;
import com.example.restaurantapi.repository.RoleRepository;
import com.example.restaurantapi.services.user.implementation.UserRoleService;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableMongoRepositories
public class RoleController {
    private final UserRoleService userRoleService;
    private final RoleRepository roleRepository;

    RoleController(UserRoleService userRoleService, RoleRepository roleRepository){
        this.roleRepository = roleRepository;
        this.userRoleService = userRoleService;
    }

    @PostMapping("/role")
    Role newRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }
}
