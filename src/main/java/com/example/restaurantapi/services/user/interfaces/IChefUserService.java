package com.example.restaurantapi.services.user.interfaces;

import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.model.user.EmployeeUser;

public interface IChefUserService {
    JwtAuthenticationResponse saveNewChefUser(EmployeeUser employeeUser);

}
