package com.example.restaurantapi.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("chefs")
public class Chef extends Employee{

    public Chef(){
        super();
    }

}
