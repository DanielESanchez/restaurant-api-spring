package com.example.restaurantapi.services.user.implementation;

import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.model.person.Waiter;
import com.example.restaurantapi.model.user.EmployeeUser;
import com.example.restaurantapi.model.user.UserRole;
import com.example.restaurantapi.repository.WaiterRepository;
import com.example.restaurantapi.services.employee.implementation.EmployeeService;
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
    private final EmployeeService checkEmployeeService;
    private final GetUserRoleService getUserRoleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;
    private final WaiterRepository waiterRepository;

    public WaiterUserService(EmployeeService checkEmployeeService, GetUserRoleService getUserRoleService, PasswordEncoder passwordEncoder, AuthenticationService authenticationService, WaiterRepository waiterRepository) {
        this.checkEmployeeService = checkEmployeeService;
        this.getUserRoleService = getUserRoleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
        this.waiterRepository = waiterRepository;
    }

    @Override
    public JwtAuthenticationResponse saveNewWaiterUser(EmployeeUser employeeUser) {
        String jobFoundForUser = checkEmployeeService.findEmployeeJob(employeeUser.getIdEmployee());
        System.out.println(jobFoundForUser);
        if(jobFoundForUser == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Employee not found.");
        }
        if(!jobFoundForUser.equals("waiter")){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "The employee is not a waiter.");
        }
        Waiter waiterEmployee = waiterRepository.findByIdEmployee(employeeUser.getIdEmployee());
        employeeUser.setFirstName(waiterEmployee.getName());
        employeeUser.setLastName(waiterEmployee.getLastName());
        Set<UserRole> roles = new HashSet<>();
        roles.add(getUserRoleService.getUserRole());
        roles.add(getUserRoleService.getWaiterRole());
        employeeUser.setUserRoles(roles);
        employeeUser.setCreatedAt(new Date().toString());
        employeeUser.setPassword(passwordEncoder.encode(employeeUser.getPassword()));
        return authenticationService.signup(employeeUser);
    }
}
