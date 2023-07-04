package com.example.restaurantapi.services.user.interfaces;

import com.example.restaurantapi.model.UserRole;

public interface IGetUserRoleService {
    UserRole getAdminRole();
    UserRole getUserRole();
    UserRole getChefRole();
    UserRole getWaiterRole();
    UserRole getCashierRole();
}
