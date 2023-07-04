package com.example.restaurantapi.controller.user;

import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.model.User;
import com.example.restaurantapi.model.UserRole;
import com.example.restaurantapi.services.security.implementation.AuthenticationService;
import com.example.restaurantapi.services.user.implementation.GetUserRoleService;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@EnableMongoRepositories
public class UserController {
    private PasswordEncoder passwordEncoder;
    private GetUserRoleService getUserRoleService;
    private AuthenticationService authenticationService;

    UserController(GetUserRoleService getUserRoleService, AuthenticationService authenticationService, PasswordEncoder passwordEncoder){
        this.getUserRoleService = getUserRoleService;
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    /*@PostMapping("/user/chef")
    ResponseEntity<JwtAuthenticationResponse> saveChef(@RequestBody User user) {
        Set<UserRole> roles = new HashSet<>();
        roles.add(getUserRoleService.getUserRole());
        roles.add(getUserRoleService.getChefRole());
        user.setUserRoles(roles);
        user.setCreatedAt(new Date().getTime());
        user.setAccountNonExpired(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(authenticationService.signup(user));
    }*/

    @PostMapping("/user/waiter")
    ResponseEntity<JwtAuthenticationResponse> saveWaiter(@RequestBody User user) {
        Set<UserRole> roles = new HashSet<>();
        roles.add(getUserRoleService.getUserRole());
        roles.add(getUserRoleService.getWaiterRole());
        user.setUserRoles(roles);
        user.setCreatedAt(new Date().getTime());
        user.setAccountNonExpired(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(authenticationService.signup(user));
    }

    @PostMapping("/user/cashier")
    ResponseEntity<JwtAuthenticationResponse> saveCashier(@RequestBody User user) {
        Set<UserRole> roles = new HashSet<>();
        roles.add(getUserRoleService.getUserRole());
        roles.add(getUserRoleService.getCashierRole());
        user.setUserRoles(roles);
        user.setCreatedAt(new Date().getTime());
        user.setAccountNonExpired(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(authenticationService.signup(user));
    }

    @PostMapping("/user/login")
    ResponseEntity<JwtAuthenticationResponse> saveUser(@RequestBody User user) {
        Set<UserRole> roles = new HashSet<>();
        roles.add(getUserRoleService.getUserRole());
        user.setUserRoles(roles);
        user.setCreatedAt(new Date().getTime());
        user.setAccountNonExpired(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(authenticationService.signup(user));
    }
}
