package com.example.restaurantapi.services.user.interfaces;

import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.model.EmployeeUser;

public interface IWaiterUserService {
    JwtAuthenticationResponse saveNewWaiterUser(EmployeeUser employeeUser);
}
