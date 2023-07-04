package com.example.restaurantapi.controller.user;

import com.example.restaurantapi.dao.request.ChangePasswordRequest;
import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.model.EmployeeUser;
import com.example.restaurantapi.services.user.implementation.ChangeDataUserService;
import com.example.restaurantapi.services.user.implementation.WaiterUserService;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableMongoRepositories
public class WaiterUserController {

    private final ChangeDataUserService changeDataUserService;
    private final WaiterUserService waiterUserService;

    WaiterUserController(ChangeDataUserService changeDataUserService, WaiterUserService waiterUserService){
        this.changeDataUserService = changeDataUserService;
        this.waiterUserService = waiterUserService;
    }

    @PostMapping("/waiter/user/new")
    ResponseEntity<JwtAuthenticationResponse> saveWaiter(@RequestBody EmployeeUser employeeUser) {
        return ResponseEntity.ok(waiterUserService.saveNewWaiterUser(employeeUser));
    }

    @PutMapping("/waiter/user/password")
    ResponseEntity<String> changePasswordWaiter(@RequestBody ChangePasswordRequest changePasswordRequest, @RequestHeader("Authorization") String header) {
        return ResponseEntity.ok(changeDataUserService.changePassword(changePasswordRequest, header));
    }
}
