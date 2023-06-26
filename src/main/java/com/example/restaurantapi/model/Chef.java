package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("chefs")
public class Chef extends Employee{
    @Getter
    @Setter
    private String job;
    @Getter
    @Setter
    private String  hireDate;
    @Getter
    @Setter
    private boolean isWorking;
    @Getter
    @Setter
    private float salary;

    public Chef(){
        super();
    }

}
