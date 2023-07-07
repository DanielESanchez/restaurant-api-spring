package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Document("menu")
public class MenuItemRestaurant {

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
    private String image;

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
