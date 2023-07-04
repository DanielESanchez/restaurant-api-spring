package com.example.restaurantapi.controller.user;

import com.example.restaurantapi.dao.request.ChangeDataUserRequest;
import com.example.restaurantapi.dao.request.ChangePasswordRequest;
import com.example.restaurantapi.dao.request.LoginRequest;
import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.services.security.implementation.JwtService;
import com.example.restaurantapi.services.user.implementation.AdminUserService;
import com.example.restaurantapi.model.EmployeeUser;

import com.example.restaurantapi.services.user.implementation.ChangeDataUserService;
import com.example.restaurantapi.services.security.implementation.AuthenticationService;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
@EnableMongoRepositories
public class AdminUserController {

    private final AdminUserService adminUserService;
    private final ChangeDataUserService changeDataUserService;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    AdminUserController(AdminUserService adminUserService, ChangeDataUserService changeDataUserService, AuthenticationService authenticationService, JwtService jwtService){
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.changeDataUserService = changeDataUserService;
        this.adminUserService = adminUserService;
    }

    @PostMapping("/admin/login")
    ResponseEntity<JwtAuthenticationResponse> loginAdmin(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }

    @PostMapping("/admin/new")
    ResponseEntity<JwtAuthenticationResponse> saveAdmin(@RequestBody EmployeeUser employeeUser) {
        return ResponseEntity.ok(adminUserService.saveNewAdmin(employeeUser));
    }

    @PutMapping("/admin/password")
    ResponseEntity<String> changePasswordAdmin(@RequestBody ChangePasswordRequest changePasswordRequest, @RequestHeader("Authorization") String header) {
        return ResponseEntity.ok(changeDataUserService.changePassword(changePasswordRequest, header));
    }

    @PutMapping("/admin/lock-user")
    ResponseEntity<String> lockUser(@RequestBody ChangeDataUserRequest changeDataUserRequest) {
        return ResponseEntity.ok(changeDataUserService.lockAccount(changeDataUserRequest.getUsername()));
    }
    @PutMapping("/admin/unlock-user")
    ResponseEntity<String> unlockUser(@RequestBody ChangeDataUserRequest changeDataUserRequest) {
        return ResponseEntity.ok(changeDataUserService.unlockAccount(changeDataUserRequest.getUsername()));
    }
}
