package com.example.restaurantapi.controller.user;

import com.example.restaurantapi.dao.request.ChangePasswordRequest;
import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.dao.response.ResponseOk;
import com.example.restaurantapi.model.user.EmployeeUser;
import com.example.restaurantapi.services.user.implementation.CashierUserService;
import com.example.restaurantapi.services.user.implementation.ChangeDataUserService;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@EnableMongoRepositories
public class CashierUserController {
    private final ChangeDataUserService changeDataUserService;
    private final CashierUserService cashierUserService;

    public CashierUserController(ChangeDataUserService changeDataUserService, CashierUserService cashierUserService) {
        this.changeDataUserService = changeDataUserService;
        this.cashierUserService = cashierUserService;
    }

    @PostMapping("/cashier/user/new")
    ResponseEntity<JwtAuthenticationResponse> saveCashier(@RequestBody EmployeeUser employeeUser) {
        return ResponseEntity.ok(cashierUserService.saveNewCashierUser(employeeUser));
    }

    @PutMapping("/cashier/user/password")
    ResponseEntity<ResponseOk> changePasswordCashier(@RequestBody ChangePasswordRequest changePasswordRequest, @RequestHeader("Authorization") String header) {
        return ResponseEntity.ok(ResponseOk
                .builder()
                .response( changeDataUserService.changePassword(changePasswordRequest, header) )
                .build());
    }
}
