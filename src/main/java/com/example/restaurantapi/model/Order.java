package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("orders")
public class Order {

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
    private Waiter waiterAssigned;

    @Getter
    @Setter
    private Boolean isCompleted;

    @Getter
    @Setter
    private Table table;

}