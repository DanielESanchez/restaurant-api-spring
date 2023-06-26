package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("menu")
public class Menu {

    @Id
    @Getter
    @Setter
    private String _id;

    @Getter
    @Setter
    private String productId;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private float price;

    @Getter
    @Setter
    private String consumable;

    @Getter
    @Setter
    private String cuisineName;

    @Getter
    @Setter
    private String categoryName;

    @Getter
    @Setter
    private String description;

}
