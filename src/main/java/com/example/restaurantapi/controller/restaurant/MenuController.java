package com.example.restaurantapi.controller.restaurant;

import com.example.restaurantapi.dao.response.ResponseOk;
import com.example.restaurantapi.model.restaurant.MenuItemRestaurant;
import com.example.restaurantapi.services.restaurant.implementation.MenuService;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@EnableMongoRepositories
@RequestMapping("api")
public class MenuController {

    private final MenuService menuService;

    MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menus")
    List<MenuItemRestaurant> allMenu() {
        return menuService.allMenu();
    }

    @GetMapping("/menu/get/{productId}")
    MenuItemRestaurant findId(@PathVariable String productId) {
        return menuService.findId(productId);
    }

    @PostMapping("/menu/new")
    ResponseEntity<ResponseOk> newMenu(@RequestBody MenuItemRestaurant menu) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response(menuService.newMenu(menu) )
                        .build());
    }

    @PutMapping("/menu/update/{productId}")
    ResponseEntity<ResponseOk> replaceMenu(@RequestBody MenuItemRestaurant newMenu) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( menuService.replaceMenu(newMenu) )
                        .build());
    }

    @DeleteMapping("/menu/delete/{productId}")
    ResponseEntity<ResponseOk> deleteMenu(@PathVariable String productId) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( menuService.deleteMenu(productId) )
                        .build());
    }

}
