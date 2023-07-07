package com.example.restaurantapi.services.user.implementation;

import com.example.restaurantapi.dao.request.ChangePasswordRequest;
import com.example.restaurantapi.model.User;
import com.example.restaurantapi.repository.UserRepository;
import com.example.restaurantapi.services.security.implementation.JwtService;
import com.example.restaurantapi.services.user.interfaces.IChangeDataUserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ChangeDataUserService implements IChangeDataUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    ChangeDataUserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public String changePassword(ChangePasswordRequest changePasswordRequest, String header) {
        final String jwt = header.substring(7);
        final String username = jwtService.extractUsername(jwt);
        User user = userRepository.findUserByUsername(username);
        Boolean result = passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword());
        if(!result){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Password is not correct.");
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
        return "Password changed";
    }

    @Override
    public String lockAccount(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) notFoundUser();
        user.setAccountNonLocked(false);
        userRepository.save(user);
        return "The account " + user.getUsername() + " is now locked";
    }

    @Override
    public String unlockAccount(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) notFoundUser();
        user.setAccountNonLocked(true);
        userRepository.save(user);
        return "The account " + user.getUsername() + " is now unlocked";
    }

    @Override
    public String setExpiredAccount(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) notFoundUser();
        user.setAccountNonExpired(false);
        userRepository.save(user);
        return "The account " + user.getUsername() + " has been set as expired account";
    }

    @Override
    public String setNonExpiredAccount(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) notFoundUser();
        user.setCredentialsNonExpired(true);
        userRepository.save(user);
        return "The expiration for the account " + user.getUsername() + " has been removed";
    }

    @Override
    public String enableAccount(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) notFoundUser();
        user.setEnabled(true);
        userRepository.save(user);
        return "The account " + user.getUsername() + " is now enabled";
    }

    @Override
    public String disableAccount(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) notFoundUser();
        user.setEnabled(false);
        userRepository.save(user);
        return "The account " + user.getUsername() + " is now enabled";
    }

    @Override
    public String setCredentialsExpiredAccount(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) notFoundUser();
        user.setCredentialsNonExpired(false);
        userRepository.save(user);
        return "The credentials for account " + user.getUsername() + " has been set as expired";
    }

    @Override
    public String setCredentialsNonExpiredAccount(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) notFoundUser();
        user.setCredentialsNonExpired(false);
        userRepository.save(user);
        return "The expiration of credentials for account " + user.getUsername() + " has been removed";
    }

    private void notFoundUser(){
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User not found.");
    }
}
