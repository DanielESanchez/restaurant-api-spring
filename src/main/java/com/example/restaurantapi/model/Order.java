package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Order {

    @Id
    @Getter
    private String _id;

    @Getter
    @Setter
    private long orderNumber;

    @Getter
    @Setter
    private List<OrderItem> orderList;

    @Getter
    @Setter
    private Waiter waiterAssigned;

    @Getter
    @Setter
    private boolean isCompleted;

    @Getter
    @Setter
    private Table table;

}