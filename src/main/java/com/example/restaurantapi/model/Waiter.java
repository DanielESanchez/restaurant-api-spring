package com.example.restaurantapi.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("employees")
public class Waiter extends Employee {

    public Waiter(){
        super();
    }

}
