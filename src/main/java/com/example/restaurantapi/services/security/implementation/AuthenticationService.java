package com.example.restaurantapi.services.security.implementation;

import com.example.restaurantapi.dao.request.LoginRequest;
import com.example.restaurantapi.dao.response.JwtAuthenticationResponse;
import com.example.restaurantapi.model.user.EmployeeUser;
import com.example.restaurantapi.model.user.User;
import com.example.restaurantapi.model.user.UserRole;
import com.example.restaurantapi.repository.UserRepository;
import com.example.restaurantapi.services.security.interfaces.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(User user) {
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        Date expiration = jwtService.extractExpiration(jwt);
        Set<UserRole> roles = user.getUserRoles();
        return JwtAuthenticationResponse.builder().token(jwt).expiration(expiration).roles(roles).build();
    }

    @Override
    public JwtAuthenticationResponse signupEmployee(EmployeeUser employeeUser) {
        userRepository.save(employeeUser);
        String jwt = jwtService.generateToken(employeeUser);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signupAdmin(User admin) {
        userRepository.save(admin);
        String jwt = jwtService.generateToken(admin);
        Date expiration = jwtService.extractExpiration(jwt);
        Set<UserRole> roles = admin.getAuthorities();
        return JwtAuthenticationResponse.builder().token(jwt).expiration(expiration).roles(roles).build();
    }

    @Override
    public JwtAuthenticationResponse login(LoginRequest request) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(request.getUsername());
        if(user== null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User or Password Incorrect.");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String jwt = jwtService.generateToken(user);
        Date expiration = jwtService.extractExpiration(jwt);
        Set<UserRole> roles = user.getUserRoles();
        return JwtAuthenticationResponse.builder().token(jwt).expiration(expiration).roles(roles).build();
    }
}
