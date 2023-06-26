package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;

public class Employee extends Person {
    @Getter
    @Setter
    private String idEmployee;
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

    public Employee(){
        super();
    }

}