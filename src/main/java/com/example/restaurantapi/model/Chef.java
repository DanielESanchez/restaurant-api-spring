package com.example.restaurantapi.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("employees")
public class Chef extends Employee{

    public Chef(){
        super();
    }

}
