package com.example.restaurantapi.services.user.implementation;

import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.model.person.Chef;
import com.example.restaurantapi.model.user.EmployeeUser;
import com.example.restaurantapi.model.user.UserRole;
import com.example.restaurantapi.repository.ChefRepository;
import com.example.restaurantapi.services.employee.implementation.EmployeeService;
import com.example.restaurantapi.services.security.implementation.AuthenticationService;
import com.example.restaurantapi.services.user.interfaces.IChefUserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class ChefUserService implements IChefUserService {
    private final EmployeeService checkEmployeeService;
    private final GetUserRoleService getUserRoleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;
    private final ChefRepository chefRepository;

    ChefUserService(EmployeeService checkEmployeeService, GetUserRoleService getUserRoleService, PasswordEncoder passwordEncoder, AuthenticationService authenticationService, ChefRepository chefRepository){
        this.checkEmployeeService = checkEmployeeService;
        this.getUserRoleService = getUserRoleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
        this.chefRepository = chefRepository;
    }
    @Override
    public JwtAuthenticationResponse saveNewChefUser(EmployeeUser employeeUser) {
        String jobFoundForUser = checkEmployeeService.findEmployeeJob(employeeUser.getIdEmployee());
        if(jobFoundForUser == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Employee not found.");
        }
        if(!jobFoundForUser.equals("chef")){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "The employee is not a chef.");
        }
        Chef chefEmployee = chefRepository.findByIdEmployee(employeeUser.getIdEmployee());
        employeeUser.setFirstName(chefEmployee.getName());
        employeeUser.setLastName(chefEmployee.getLastName());
        Set<UserRole> roles = new HashSet<>();
        roles.add(getUserRoleService.getUserRole());
        roles.add(getUserRoleService.getChefRole());
        employeeUser.setUserRoles(roles);
        employeeUser.setCreatedAt(new Date().toString());
        employeeUser.setPassword(passwordEncoder.encode(employeeUser.getPassword()));
        return authenticationService.signup(employeeUser);
    }
}
