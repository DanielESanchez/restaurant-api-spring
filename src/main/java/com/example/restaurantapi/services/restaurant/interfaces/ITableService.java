package com.example.restaurantapi.services.restaurant.interfaces;

import com.example.restaurantapi.model.restaurant.Table;

import java.util.List;

public interface ITableService {
    List<Table> allTable();
    Table findId(Long tableNumber);
    String newTable(Table table);
    String replaceTable(Table newTable);
    String deleteTable(Long tableNumber);
}
