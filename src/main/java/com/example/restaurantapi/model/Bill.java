package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public class Bill {

    @Id
    @Getter
    @Setter
    private String _id;

    @Setter
    @Getter
    private String idBill;

    @Getter
    @Setter
    private Date dateBill;

    @Getter
    @Setter
    private float subtotal = 0;

    @Getter
    @Setter
    private int taxesPercentage;

    @Getter
    @Setter
    private float taxesTotal;

    @Getter
    @Setter
    private int discount;

    @Getter
    @Setter
    private float discountTotal;

    @Getter
    @Setter
    private float total;

    @Getter
    @Setter
    private List<OrderItem> items;

    @Getter
    @Setter
    private Table table;

    @Getter
    @Setter
    private Customer customer;

    @Getter
    @Setter
    private Cashier cashier;

}

