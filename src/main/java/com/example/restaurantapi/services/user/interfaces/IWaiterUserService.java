package com.example.restaurantapi.services.user.interfaces;

import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.model.user.EmployeeUser;

public interface IWaiterUserService {
    JwtAuthenticationResponse saveNewWaiterUser(EmployeeUser employeeUser);
}
