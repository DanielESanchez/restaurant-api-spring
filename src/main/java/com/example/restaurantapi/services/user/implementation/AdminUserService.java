package com.example.restaurantapi.services.user.implementation;

import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.model.User;
import com.example.restaurantapi.model.UserRole;
import com.example.restaurantapi.repository.UserRepository;
import com.example.restaurantapi.services.security.implementation.AuthenticationService;
import com.example.restaurantapi.services.user.interfaces.IAdminUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class AdminUserService implements IAdminUserService {
    private GetUserRoleService getUserRoleService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationService authenticationService;

    AdminUserService(GetUserRoleService getUserRoleService,
                     PasswordEncoder passwordEncoder,
                     AuthenticationService authenticationService){
        this.getUserRoleService = getUserRoleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
    }
    @Override
    public JwtAuthenticationResponse saveNewAdmin(User user) {
        Set<UserRole> roles = new HashSet<>();
        roles.add(getUserRoleService.getAdminRole());
        roles.add(getUserRoleService.getUserRole());
        roles.add(getUserRoleService.getCashierRole());
        roles.add(getUserRoleService.getWaiterRole());
        roles.add(getUserRoleService.getChefRole());
        user.setFirstName("ADMIN");
        user.setFirstName("ADMIN");
        user.setUserRoles(roles);
        user.setCreatedAt(new Date().toString());
        user.setAccountNonExpired(true);
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return authenticationService.signup(user);
    }

}
