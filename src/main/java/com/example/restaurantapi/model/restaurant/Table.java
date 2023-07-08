package com.example.restaurantapi.model.restaurant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tables")
public class Table {
    @Id
    @Getter
    @Setter
    private String _id;

    @Getter
    @Setter
    @Indexed(unique = true)
    private Long tableNumber;

    @Getter
    @Setter
    private Boolean isEmpty;

}
