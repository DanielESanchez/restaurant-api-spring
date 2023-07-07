package com.example.restaurantapi.controller.user;

import com.example.restaurantapi.dao.request.ChangeDataUserRequest;
import com.example.restaurantapi.dao.request.ChangePasswordRequest;
import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.dao.response.ResponseOk;
import com.example.restaurantapi.model.User;
import com.example.restaurantapi.services.user.implementation.AdminUserService;

import com.example.restaurantapi.services.user.implementation.ChangeDataUserService;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@CrossOrigin
@RestController
@EnableMongoRepositories
public class AdminUserController {

    private final AdminUserService adminUserService;
    private final ChangeDataUserService changeDataUserService;

    AdminUserController(AdminUserService adminUserService, ChangeDataUserService changeDataUserService){
        this.changeDataUserService = changeDataUserService;
        this.adminUserService = adminUserService;
    }

    @PostMapping("/admin/new")
    ResponseEntity<JwtAuthenticationResponse> saveAdmin(@RequestBody User user) {
        return ResponseEntity.ok(adminUserService.saveNewAdmin(user));
    }

    @PutMapping("/admin/password")
    ResponseEntity<ResponseOk> changePasswordAdmin(@RequestBody ChangePasswordRequest changePasswordRequest, @RequestHeader("Authorization") String header) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( changeDataUserService.changePassword(changePasswordRequest, header) )
                        .build());
    }

    @PutMapping("/admin/lock-user")
    ResponseEntity<ResponseOk> lockUser(@RequestBody ChangeDataUserRequest changeDataUserRequest) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( changeDataUserService.lockAccount(changeDataUserRequest.getUsername()) )
                        .build());
    }
    @PutMapping("/admin/unlock-user")
    ResponseEntity<ResponseOk> unlockUser(@RequestBody ChangeDataUserRequest changeDataUserRequest) {
        return ResponseEntity.ok(ResponseOk
                .builder()
                .response( changeDataUserService.unlockAccount(changeDataUserRequest.getUsername()) )
                .build());
    }

    @PutMapping("/admin/set-expired")
    ResponseEntity<ResponseOk> setExpiredUser(@RequestBody ChangeDataUserRequest changeDataUserRequest) {
        return ResponseEntity.ok(ResponseOk
                .builder()
                .response( changeDataUserService.setExpiredAccount(changeDataUserRequest.getUsername()) )
                .build());
    }

    @PutMapping("/admin/set-non-expired")
    ResponseEntity<ResponseOk> setNonExpiredUser(@RequestBody ChangeDataUserRequest changeDataUserRequest) {
        return ResponseEntity.ok(ResponseOk
                .builder()
                .response( changeDataUserService.setNonExpiredAccount(changeDataUserRequest.getUsername()) )
                .build());
    }

    @PutMapping("/admin/enable-user")
    ResponseEntity<ResponseOk> enableUser(@RequestBody ChangeDataUserRequest changeDataUserRequest) {
        return ResponseEntity.ok(ResponseOk
                .builder()
                .response( changeDataUserService.enableAccount(changeDataUserRequest.getUsername()) )
                .build());
    }

    @PutMapping("/admin/disable-user")
    ResponseEntity<ResponseOk> disableUser(@RequestBody ChangeDataUserRequest changeDataUserRequest) {
        return ResponseEntity.ok(ResponseOk
                .builder()
                .response( changeDataUserService.disableAccount(changeDataUserRequest.getUsername()) )
                .build());
    }

    @PutMapping("/admin/set-credentials-expired")
    ResponseEntity<ResponseOk> setCredentialsExpiredUser(@RequestBody ChangeDataUserRequest changeDataUserRequest) {
        return ResponseEntity.ok(ResponseOk
                .builder()
                .response( changeDataUserService.setCredentialsExpiredAccount(changeDataUserRequest.getUsername()) )
                .build());
    }

    @PutMapping("/admin/set-credentials-non-expired")
    ResponseEntity<ResponseOk> setCredentialsNonExpiredUser(@RequestBody ChangeDataUserRequest changeDataUserRequest) {
        return ResponseEntity.ok(ResponseOk
                .builder()
                .response( changeDataUserService.setCredentialsExpiredAccount(changeDataUserRequest.getUsername()) )
                .build());
    }
}
