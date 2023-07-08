package com.example.restaurantapi.model.person;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

public class Employee extends Person {
    @Getter
    @Setter
    @Indexed(unique = true)
    private String idEmployee;
    @Getter
    @Setter
    private String job;
    @Getter
    @Setter
    private String  hireDate;
    @Getter
    @Setter
    private Boolean isWorking;
    @Getter
    @Setter
    private Float salary;

    public Employee(){
        super();
    }

}