package com.example.restaurantapi.model.person;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("customers")
public class Customer extends Person {
    @Getter
    @Setter
    private String taxId;

    @Getter
    @Setter
    private Boolean isSenior;
}
