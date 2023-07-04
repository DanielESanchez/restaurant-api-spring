package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class EmployeeUser extends User{

    @Getter
    @Setter
    private String idEmployee;

}
