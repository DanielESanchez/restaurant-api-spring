package com.example.restaurantapi.controller.user;

import com.example.restaurantapi.dao.request.ChangePasswordRequest;
import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.model.EmployeeUser;
import com.example.restaurantapi.services.user.implementation.ChangeDataUserService;
import com.example.restaurantapi.services.user.implementation.ChefUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
public class ChefUserController {

    private final ChangeDataUserService changeDataUserService;
    private final ChefUserService chefUserService;

    ChefUserController(ChangeDataUserService changeDataUserService, ChefUserService chefUserService){
        this.changeDataUserService = changeDataUserService;
        this.chefUserService = chefUserService;
    }

    @PostMapping("/chef/user/new")
    ResponseEntity<JwtAuthenticationResponse> saveAdmin(@RequestBody EmployeeUser employeeUser) {
        return ResponseEntity.ok(chefUserService.saveNewChef(employeeUser));
    }

    @PutMapping("/chef/user/password")
    ResponseEntity<String> changePasswordChef(@RequestBody ChangePasswordRequest changePasswordRequest, @RequestHeader("Authorization") String header) {
        return ResponseEntity.ok(changeDataUserService.changePassword(changePasswordRequest, header));
    }
}
