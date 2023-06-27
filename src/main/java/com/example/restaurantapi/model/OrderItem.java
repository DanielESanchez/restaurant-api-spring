package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;

public class OrderItem {

    @Getter
    @Setter
    private Menu menuItem;

    @Getter
    @Setter
    private Boolean isBeingCooked;

    @Getter
    @Setter
    private Chef chefAssigned;

    @Getter
    @Setter
    private Boolean isCompleted =false;

    @Getter
    @Setter
    private Integer quantity;

    public OrderItem(Chef chefAssigned, Menu menuItem){
        super();
        this.menuItem = menuItem;
        this.chefAssigned = chefAssigned;
    }

}