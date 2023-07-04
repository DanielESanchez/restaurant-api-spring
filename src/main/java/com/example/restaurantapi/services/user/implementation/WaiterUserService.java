package com.example.restaurantapi.services.user.implementation;

import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.model.EmployeeUser;
import com.example.restaurantapi.model.UserRole;
import com.example.restaurantapi.services.implementation.CheckEmployeeService;
import com.example.restaurantapi.services.security.implementation.AuthenticationService;
import com.example.restaurantapi.services.user.interfaces.IWaiterUserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class WaiterUserService implements IWaiterUserService {
    private final CheckEmployeeService checkEmployeeService;
    private final GetUserRoleService getUserRoleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    public WaiterUserService(CheckEmployeeService checkEmployeeService, GetUserRoleService getUserRoleService, PasswordEncoder passwordEncoder, AuthenticationService authenticationService) {
        this.checkEmployeeService = checkEmployeeService;
        this.getUserRoleService = getUserRoleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
    }

    @Override
    public JwtAuthenticationResponse saveNewWaiterUser(EmployeeUser employeeUser) {
        String jobFoundForUser = checkEmployeeService.findEmployeeJob(employeeUser.getIdEmployee());
        if(jobFoundForUser == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Employee not found.");
        }
        if(!jobFoundForUser.equals("waiter")){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "The employee is not a waiter.");
        }
        Set<UserRole> roles = new HashSet<>();
        roles.add(getUserRoleService.getUserRole());
        roles.add(getUserRoleService.getWaiterRole());
        employeeUser.setUserRoles(roles);
        employeeUser.setCreatedAt(new Date().getTime());
        employeeUser.setPassword(passwordEncoder.encode(employeeUser.getPassword()));
        return authenticationService.signup(employeeUser);
    }
}
