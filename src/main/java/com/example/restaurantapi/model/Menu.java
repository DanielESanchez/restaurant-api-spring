package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@Document("menu")
@Validated
public class Menu {

    @Id
    @Getter
    @Setter
    private String _id;

    @Getter
    @Setter
    @Indexed(unique = true)
    private String productId;

    @Getter
    @Setter
    @Indexed(unique = true)
    private String name;

    @Getter
    @Setter
    private Float price;

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
