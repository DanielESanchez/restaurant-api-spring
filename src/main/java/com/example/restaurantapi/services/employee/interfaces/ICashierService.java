package com.example.restaurantapi.services.employee.interfaces;

import com.example.restaurantapi.model.person.Cashier;

import java.util.List;

public interface ICashierService {
    List<Cashier> getAllCashiers();
    List<Cashier> getAvailableCashiers();
    Cashier findCashierByIdEmployee(String idEmployee);
    String saveCashier(Cashier cashier);
    String updateCashier(Cashier newCashier);
    String deleteCashier(String idEmployee);
}
