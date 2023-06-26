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
    private long tableNumber;

    @Getter
    @Setter
    private boolean isEmpty;

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public void changeStatusTable() {
        isEmpty = !isEmpty;
    }
}
