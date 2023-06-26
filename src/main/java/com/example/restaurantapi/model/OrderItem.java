package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;

public class OrderItem {

    @Getter
    @Setter
    private Menu menuItem;

    @Getter
    @Setter
    private boolean isBeingCooked;

    @Getter
    @Setter
    private Chef chefAssigned;

    @Getter
    @Setter
    private boolean isCompleted =false;

    @Getter
    @Setter
    private int quantity;

    public OrderItem(Chef chefAssigned, Menu menuItem){
        super();
        this.menuItem = menuItem;
        this.chefAssigned = chefAssigned;
    }

}