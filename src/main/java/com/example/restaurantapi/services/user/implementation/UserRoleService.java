package com.example.restaurantapi.services.user.implementation;

import com.example.restaurantapi.model.user.UserRole;
import com.example.restaurantapi.services.user.interfaces.IUserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService implements IUserRoleService {
    @Override
    public UserRole setUserRole(){
        return new UserRole();
    }
}
