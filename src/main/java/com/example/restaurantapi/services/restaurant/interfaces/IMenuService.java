package com.example.restaurantapi.services.restaurant.interfaces;

import com.example.restaurantapi.model.restaurant.MenuItemRestaurant;

import java.util.List;

public interface IMenuService {
    List<MenuItemRestaurant> allMenu();
    MenuItemRestaurant findId(String productId);
    String newMenu(MenuItemRestaurant menu);
    String replaceMenu(MenuItemRestaurant newMenu);
    String deleteMenu(String productId);
}
