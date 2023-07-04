package com.example.restaurantapi.services.security.interfaces;

import com.example.restaurantapi.dao.request.LoginRequest;
import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.model.EmployeeUser;
import com.example.restaurantapi.model.User;

public interface IAuthenticationService {
    JwtAuthenticationResponse signup(User user);

    JwtAuthenticationResponse login(LoginRequest request);
    JwtAuthenticationResponse signupEmployee(EmployeeUser employeeUser);
    JwtAuthenticationResponse signupAdmin(User admin);
}
