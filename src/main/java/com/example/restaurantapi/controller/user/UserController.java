package com.example.restaurantapi.controller.user;

import com.example.restaurantapi.dao.request.LoginRequest;
import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.dao.response.ResponseOk;
import com.example.restaurantapi.model.EmailDetails;
import com.example.restaurantapi.model.user.User;
import com.example.restaurantapi.model.user.UserRole;
import com.example.restaurantapi.services.security.implementation.AuthenticationService;
import com.example.restaurantapi.services.user.implementation.GetUserRoleService;
import com.example.restaurantapi.services.user.implementation.RecoverPasswordService;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin
@RestController
@EnableMongoRepositories
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final GetUserRoleService getUserRoleService;
    private final AuthenticationService authenticationService;
    private final RecoverPasswordService recoverPassword;

    UserController(GetUserRoleService getUserRoleService, AuthenticationService authenticationService, PasswordEncoder passwordEncoder, RecoverPasswordService recoverPassword){
        this.getUserRoleService = getUserRoleService;
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
        this.recoverPassword = recoverPassword;
    }

    @PostMapping("/user/new")
    ResponseEntity<JwtAuthenticationResponse> saveUser(@RequestBody User user) {
        Set<UserRole> roles = new HashSet<>();
        roles.add(getUserRoleService.getUserRole());
        user.setUserRoles(roles);
        user.setCreatedAt(new Date().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(authenticationService.signup(user));
    }

    @PostMapping("/user/login")
    ResponseEntity<JwtAuthenticationResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }

    @PostMapping("/user/recover")
    ResponseEntity<ResponseOk> recoverPassword(@RequestBody EmailDetails emailDetails) {
        return ResponseEntity.ok(ResponseOk
                .builder()
                .response( recoverPassword.recoverPassword(emailDetails) )
                .build());
    }
}
