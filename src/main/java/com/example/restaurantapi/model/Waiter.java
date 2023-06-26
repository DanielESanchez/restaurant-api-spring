package com.example.restaurantapi.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("cashiers")
public class Waiter extends Employee {

    public Waiter(){
        super();
    }

}
