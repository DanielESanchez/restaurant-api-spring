package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;

public class Customer extends Person {
    @Getter
    @Setter
    private String taxId;

    @Getter
    @Setter
    private boolean isSenior;
}
