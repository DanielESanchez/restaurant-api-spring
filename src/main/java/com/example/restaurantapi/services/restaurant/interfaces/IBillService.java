package com.example.restaurantapi.services.restaurant.interfaces;

import com.example.restaurantapi.model.restaurant.Bill;

import java.util.List;

public interface IBillService {
    List<Bill> allBill();
    Bill findById(String idBill);
    String newBill(Bill bill);
    String updateBill(Bill newBill);
    String deleteBill(String idBill);
}
