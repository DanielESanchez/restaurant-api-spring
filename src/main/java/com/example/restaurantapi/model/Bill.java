package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;
import java.util.List;

public class Bill {

    @Id
    @Getter
    @Setter
    private String _id;

    @Setter
    @Getter
    @Indexed(unique = true)
    private String idBill;

    @Getter
    @Setter
    private Date dateBill;

    @Getter
    @Setter
    private Float subtotal;

    @Getter
    @Setter
    private Integer taxesPercentage;

    @Getter
    @Setter
    private Float taxesTotal;

    @Getter
    @Setter
    private Integer discount;

    @Getter
    @Setter
    private Float discountTotal;

    @Getter
    @Setter
    private Float total;

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

