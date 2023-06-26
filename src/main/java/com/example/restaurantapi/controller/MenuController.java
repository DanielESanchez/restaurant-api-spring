package com.example.restaurantapi.controller;

import com.example.restaurantapi.model.Menu;
import com.example.restaurantapi.repository.MenuRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@EnableMongoRepositories
public class MenuController {

    private final MenuRepository repository;

    MenuController(MenuRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/menus")
    List<Menu> allMenu() {
        return repository.findAll();
    }

    @GetMapping("/menu/{productId}")
    String findId(@PathVariable String productId) {
        Menu menu = repository.findItemByProductId(productId);
        return (menu == null) ? "404" : menu.get_id();
    }

    @PostMapping("/menu")
    Menu newMenu(@RequestBody Menu menu) {
        return repository.save(menu);
    }

    @PutMapping("/menu/{productId}")
    Menu replaceMenu(@RequestBody Menu newMenu, @PathVariable String productId) {
        Menu oldMenu = repository.findItemByProductId(productId);
        if(oldMenu == null) return null;
        String _id = oldMenu.get_id();
        return repository.findById(_id)
                .map(menu -> {
                    menu.setName(newMenu.getName());
                    menu.setConsumable(newMenu.getConsumable());
                    menu.setDescription(newMenu.getDescription());
                    menu.setCuisineName(newMenu.getCuisineName());
                    menu.setPrice(newMenu.getPrice());
                    menu.setCategoryName(newMenu.getCategoryName());
                    return repository.save(menu);
                })
                .orElseGet(() -> repository.save(newMenu));
    }

    @DeleteMapping("/menu/{productId}")
    String deleteMenu(@PathVariable String productId) {
        Menu menu = repository.findItemByProductId(productId);
        if(menu == null) return "Not Found";
        String _id = menu.get_id();
        repository.deleteById(_id);
        return "Completed";
    }
}
