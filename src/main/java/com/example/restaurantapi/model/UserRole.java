package com.example.restaurantapi.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

public class UserRole implements GrantedAuthority {

    @Getter
    @Setter
    private Role role;

    @Override
    public String getAuthority() {
        return role.getName();
    }
}

