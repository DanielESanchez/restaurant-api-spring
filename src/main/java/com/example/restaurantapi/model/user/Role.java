package com.example.restaurantapi.model.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("roles")
public class Role {

    @Getter
    @Setter
    private String name;
}
