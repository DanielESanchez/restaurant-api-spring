package com.example.restaurantapi.model.person;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("employees")
public class Cashier extends Employee {

    public Cashier(){
        super();
    }

}
