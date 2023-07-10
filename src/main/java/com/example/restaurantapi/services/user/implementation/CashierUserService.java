package com.example.restaurantapi.services.user.implementation;

import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.model.person.Cashier;
import com.example.restaurantapi.model.user.EmployeeUser;
import com.example.restaurantapi.model.user.UserRole;
import com.example.restaurantapi.repository.CashierRepository;
import com.example.restaurantapi.services.employee.implementation.EmployeeService;
import com.example.restaurantapi.services.security.implementation.AuthenticationService;
import com.example.restaurantapi.services.user.interfaces.ICashierUserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class CashierUserService implements ICashierUserService {
    private final EmployeeService checkEmployeeService;
    private final GetUserRoleService getUserRoleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;
    private final CashierRepository cashierRepository;

    public CashierUserService(EmployeeService checkEmployeeService, GetUserRoleService getUserRoleService, PasswordEncoder passwordEncoder, AuthenticationService authenticationService, CashierRepository cashierRepository) {
        this.checkEmployeeService = checkEmployeeService;
        this.getUserRoleService = getUserRoleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
        this.cashierRepository = cashierRepository;
    }

    @Override
    public JwtAuthenticationResponse saveNewCashierUser(EmployeeUser employeeUser) {
        String jobFoundForUser = checkEmployeeService.findEmployeeJob(employeeUser.getIdEmployee());
        if(jobFoundForUser == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Employee not found.");
        }
        Cashier cashierEmployee = cashierRepository.findByIdEmployee(employeeUser.getIdEmployee());
        if(!jobFoundForUser.equals("cashier")){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "The employee is not a cashier.");
        }
        employeeUser.setFirstName(cashierEmployee.getName());
        employeeUser.setLastName(cashierEmployee.getLastName());
        Set<UserRole> roles = new HashSet<>();
        roles.add(getUserRoleService.getUserRole());
        roles.add(getUserRoleService.getCashierRole());
        employeeUser.setUserRoles(roles);
        employeeUser.setCreatedAt(new Date().toString());
        employeeUser.setPassword(passwordEncoder.encode(employeeUser.getPassword()));
        return authenticationService.signup(employeeUser);
    }
}
