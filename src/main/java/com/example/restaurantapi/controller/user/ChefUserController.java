package com.example.restaurantapi.controller.user;

import com.example.restaurantapi.dao.request.ChangePasswordRequest;
import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.dao.response.ResponseOk;
import com.example.restaurantapi.model.user.EmployeeUser;
import com.example.restaurantapi.services.user.implementation.ChangeDataUserService;
import com.example.restaurantapi.services.user.implementation.ChefUserService;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@EnableMongoRepositories
@RequestMapping("api")
public class ChefUserController {

    private final ChangeDataUserService changeDataUserService;
    private final ChefUserService chefUserService;

    ChefUserController(ChangeDataUserService changeDataUserService, ChefUserService chefUserService){
        this.changeDataUserService = changeDataUserService;
        this.chefUserService = chefUserService;
    }

    @PostMapping("/chef/user/new")
    ResponseEntity<JwtAuthenticationResponse> saveChef(@RequestBody EmployeeUser employeeUser) {
        return ResponseEntity.ok(chefUserService.saveNewChefUser(employeeUser));
    }

    @PutMapping("/chef/user/password")
    ResponseEntity<ResponseOk> changePasswordChef(@RequestBody ChangePasswordRequest changePasswordRequest, @RequestHeader("Authorization") String header) {
        return ResponseEntity.ok(ResponseOk
                .builder()
                .response( changeDataUserService.changePassword(changePasswordRequest, header) )
                .build());
    }
}
