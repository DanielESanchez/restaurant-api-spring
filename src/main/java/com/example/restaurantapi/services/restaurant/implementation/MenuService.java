package com.example.restaurantapi.services.restaurant.implementation;

import com.example.restaurantapi.model.restaurant.MenuItemRestaurant;
import com.example.restaurantapi.repository.MenuRepository;
import com.example.restaurantapi.services.AttributeCheckerService;
import com.example.restaurantapi.services.restaurant.interfaces.IMenuService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MenuService implements IMenuService {
    private final MenuRepository menuRepository;
    private final AttributeCheckerService attributeCheckerService;

    public MenuService(MenuRepository menuRepository, AttributeCheckerService attributeCheckerService) {
        this.menuRepository = menuRepository;
        this.attributeCheckerService = attributeCheckerService;
    }

    @Override
    public List<MenuItemRestaurant> allMenu() {
        List<MenuItemRestaurant> menuList = menuRepository.findAll();
        if (menuList.size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There are no items to show, please add a new item to the menu");
        }
        return menuList;
    }

    @Override
    public MenuItemRestaurant findId(String productId) {
        MenuItemRestaurant menu = menuRepository.findItemByProductId(productId);
        if (menu == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Menu Item Not Found");
        }
        return menu;
    }

    @Override
    public String newMenu(MenuItemRestaurant menu) {
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(menu);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        menuRepository.save(menu);
        return menu.getName() + " was successfully added to the menu.";
    }

    @Override
    public String replaceMenu(MenuItemRestaurant newMenu) {
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(newMenu);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        MenuItemRestaurant oldMenu = menuRepository.findItemByProductId(newMenu.getProductId());
        if(oldMenu == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Menu Item " + newMenu.getProductId() + " was not found");
        }
        String _id = oldMenu.get_id();
        return menuRepository.findById(_id)
                .map(menu -> {
                    menu.setName(newMenu.getName());
                    menu.setConsumable(newMenu.getConsumable());
                    menu.setDescription(newMenu.getDescription());
                    menu.setCuisineName(newMenu.getCuisineName());
                    menu.setPrice(newMenu.getPrice());
                    menu.setCategoryName(newMenu.getCategoryName());
                    menuRepository.save(menu);
                    return menu.getName() + " was successfully updated.";
                })
                .orElseGet(() -> {
                    menuRepository.save(newMenu);
                    return newMenu.getName() + " was added to the menu.";
                });
    }

    @Override
    public String deleteMenu(String productId) {
        MenuItemRestaurant menu = menuRepository.findItemByProductId(productId);
        if(menu == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Menu Item Not Found");
        }
        String _id = menu.get_id();
        menuRepository.deleteById(_id);
        return menu.getName() + " was successfully deleted.";
    }
}
