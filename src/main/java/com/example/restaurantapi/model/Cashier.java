package com.example.restaurantapi.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("cashiers")
public class Cashier extends Employee {

    public Cashier(){
        super();
    }

}
