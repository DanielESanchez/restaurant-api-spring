package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

public class Table {
    @Id
    @Getter
    @Setter
    private String _id;

    @Getter
    @Setter
    private int tableNumber;

    @Getter
    @Setter
    private boolean isEmpty;

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public void changeStatusTable() {
        isEmpty = !isEmpty;
    }
}
