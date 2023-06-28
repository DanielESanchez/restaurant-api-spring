package com.example.restaurantapi.controller;

import com.example.restaurantapi.model.MenuItemRestaurant;
import com.example.restaurantapi.repository.MenuRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.example.restaurantapi.services.AttributeNullCheckerService;
import java.util.List;

@RestController
@EnableMongoRepositories
public class MenuController {

    private final MenuRepository repository;
    private final AttributeNullCheckerService attributeNullCheckerService;

    MenuController(MenuRepository repository, AttributeNullCheckerService attributeNullCheckerService) {
        this.attributeNullCheckerService = attributeNullCheckerService;
        this.repository = repository;
    }

    @GetMapping("/menus")
    List<MenuItemRestaurant> allMenu() {
        List<MenuItemRestaurant> menuList = repository.findAll();
        if (menuList.size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There are no items to show, please add a new item to the menu");
        }
        return menuList;
    }

    @GetMapping("/menu/{productId}")
    String findId(@PathVariable String productId) {
        MenuItemRestaurant menu = repository.findItemByProductId(productId);
        if (menu == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Menu Item Not Found");
        }
        return menu.get_id();
    }

    @PostMapping("/menu")
    ResponseEntity newMenu(@RequestBody MenuItemRestaurant menu) {
        String messageResponseFromNullTest = attributeNullCheckerService.checkNullsInObject(menu);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        repository.save(menu);
        return new ResponseEntity<>(
                menu.getName() + " was successfully added to the menu.",
                HttpStatus.OK);
    }

    @PutMapping("/menu/{productId}")
    ResponseEntity replaceMenu(@RequestBody MenuItemRestaurant newMenu, @PathVariable String productId) {
        String messageResponseFromNullTest = attributeNullCheckerService.checkNullsInObject(newMenu);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        MenuItemRestaurant oldMenu = repository.findItemByProductId(productId);
        if(oldMenu == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Menu Item " + productId + " was not found");
        }
        String _id = oldMenu.get_id();
        return repository.findById(_id)
                .map(menu -> {
                    menu.setName(newMenu.getName());
                    menu.setConsumable(newMenu.getConsumable());
                    menu.setDescription(newMenu.getDescription());
                    menu.setCuisineName(newMenu.getCuisineName());
                    menu.setPrice(newMenu.getPrice());
                    menu.setCategoryName(newMenu.getCategoryName());
                    repository.save(menu);
                    return new ResponseEntity<>(
                            menu.getName() + " was successfully updated.",
                            HttpStatus.OK);
                })
                .orElseGet(() -> {
                    repository.save(newMenu);
                    return new ResponseEntity<>(
                            newMenu.getName() + " was added to the menu.",
                            HttpStatus.OK);
                });
    }

    @DeleteMapping("/menu/{productId}")
    ResponseEntity deleteMenu(@PathVariable String productId) {
        MenuItemRestaurant menu = repository.findItemByProductId(productId);
        if(menu == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Menu Item Not Found");
        }
        String _id = menu.get_id();
        repository.deleteById(_id);
        return new ResponseEntity<>(
                menu.getName() + " was successfully deleted.",
                HttpStatus.OK);
    }

}
