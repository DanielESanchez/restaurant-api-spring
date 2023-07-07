package com.example.restaurantapi.services.user.interfaces;

import com.example.restaurantapi.dao.request.ChangePasswordRequest;

public interface IChangeDataUserService {
    String changePassword(ChangePasswordRequest changePasswordRequest, String token);
    String lockAccount(String username);
    String unlockAccount(String username);
    String setExpiredAccount(String username);
    String setNonExpiredAccount(String username);
    String enableAccount(String username);
    String disableAccount(String username);
    String setCredentialsExpiredAccount(String username);
    String setCredentialsNonExpiredAccount(String username);
}
