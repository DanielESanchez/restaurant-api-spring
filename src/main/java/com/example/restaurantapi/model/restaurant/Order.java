package com.example.restaurantapi.model.restaurant;

import com.example.restaurantapi.model.person.Waiter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("orders")
public class Order {
    @Transient
    public static final String SEQUENCE_NAME = "order_sequence";

    @Id
    @Getter
    private String _id;

    @Getter
    @Setter
    @Indexed(unique = true)
    private Long orderNumber;

    @Getter
    @Setter
    private List<OrderItem> orderList;

    @Getter
    @Setter
    private String waiterAssigned;

    @Getter
    @Setter
    private Boolean isCompleted;

    @Getter
    @Setter
    private Long table;

    @Getter
    @Setter
    private String username;

}