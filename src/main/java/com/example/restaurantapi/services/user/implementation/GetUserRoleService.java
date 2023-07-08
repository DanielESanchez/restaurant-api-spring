package com.example.restaurantapi.services.user.implementation;

import com.example.restaurantapi.model.user.Role;
import com.example.restaurantapi.model.user.UserRole;
import com.example.restaurantapi.repository.RoleRepository;
import com.example.restaurantapi.services.user.interfaces.IGetUserRoleService;
import org.springframework.stereotype.Service;

@Service
public class GetUserRoleService implements IGetUserRoleService {
    private RoleRepository roleRepository;
    private UserRoleService userRoleService;

    GetUserRoleService(RoleRepository roleRepository, UserRoleService userRoleService){
        this.roleRepository = roleRepository;
        this.userRoleService = userRoleService;
    }
    @Override
    public UserRole getAdminRole() {
        UserRole adminUserRole = userRoleService.setUserRole();
        Role roleAdmin = roleRepository.findRoleByName("ROLE_ADMIN");
        adminUserRole.setRole(roleAdmin);
        return adminUserRole;
    }

    @Override
    public UserRole getUserRole() {
        UserRole userUserRole = userRoleService.setUserRole();
        Role roleUser = roleRepository.findRoleByName("ROLE_USER");
        userUserRole.setRole(roleUser);
        return userUserRole;
    }

    @Override
    public UserRole getChefRole() {
        UserRole chefUserRole = userRoleService.setUserRole();
        Role roleChef = roleRepository.findRoleByName("ROLE_CHEF");
        chefUserRole.setRole(roleChef);
        return chefUserRole;
    }

    @Override
    public UserRole getWaiterRole() {
        UserRole waiterUserRole = userRoleService.setUserRole();
        Role roleWaiter = roleRepository.findRoleByName("ROLE_WAITER");
        waiterUserRole.setRole(roleWaiter);
        return waiterUserRole;
    }

    @Override
    public UserRole getCashierRole() {
        UserRole cashierUserRole = userRoleService.setUserRole();
        Role roleCashier = roleRepository.findRoleByName("ROLE_CASHIER");
        cashierUserRole.setRole(roleCashier);
        return cashierUserRole;
    }
}
