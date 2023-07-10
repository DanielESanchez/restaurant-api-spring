package com.example.restaurantapi.model.restaurant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document("tables")
public class Table {

    @Transient
    public static final String SEQUENCE_NAME = "table_sequence";

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
