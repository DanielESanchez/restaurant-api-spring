package com.example.restaurantapi.services.employee.interfaces;

import com.example.restaurantapi.model.Waiter;

import java.util.List;

public interface IWaiterService {
    List<Waiter> getAllWaiters();
    List<Waiter> getAvailableWaiters();
    Waiter findWaiterByIdEmployee(String idEmployee);
    String saveWaiter(Waiter waiter);
    String updateWaiter(Waiter newWaiter);
    String deleteWaiter(String idEmployee);
}
