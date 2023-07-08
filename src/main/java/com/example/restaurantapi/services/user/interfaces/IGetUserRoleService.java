package com.example.restaurantapi.services.user.interfaces;

import com.example.restaurantapi.model.user.UserRole;

public interface IGetUserRoleService {
    UserRole getAdminRole();
    UserRole getUserRole();
    UserRole getChefRole();
    UserRole getWaiterRole();
    UserRole getCashierRole();
}
