package com.example.restaurantapi.services.user.interfaces;

import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.model.User;


public interface IAdminUserService {
    JwtAuthenticationResponse saveNewAdmin(User User);

}
