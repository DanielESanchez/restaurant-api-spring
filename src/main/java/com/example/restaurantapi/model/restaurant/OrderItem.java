package com.example.restaurantapi.model.restaurant;

import com.example.restaurantapi.model.person.Chef;
import lombok.Getter;
import lombok.Setter;

public class OrderItem {

    @Getter
    @Setter
    private MenuItemRestaurant menuItem;

    @Getter
    @Setter
    private Boolean isBeingCooked = false;

    @Getter
    @Setter
    private String chefAssigned;

    @Getter
    @Setter
    private Boolean isCompleted =false;

    @Getter
    @Setter
    private Integer quantity;

    public OrderItem(String chefAssigned, MenuItemRestaurant menuItem){
        super();
        this.menuItem = menuItem;
        this.chefAssigned = chefAssigned;
    }

}