package com.example.restaurantapi.services.implementation;

import com.example.restaurantapi.model.Cashier;
import com.example.restaurantapi.model.Chef;
import com.example.restaurantapi.model.Waiter;
import com.example.restaurantapi.repository.CashierRepository;
import com.example.restaurantapi.repository.ChefRepository;
import com.example.restaurantapi.repository.WaiterRepository;
import com.example.restaurantapi.services.ICheckEmployeeService;
import org.springframework.stereotype.Service;

@Service
public class CheckEmployeeService implements ICheckEmployeeService {
    private ChefRepository chefRepository;
    private CashierRepository cashierRepository;
    private WaiterRepository waiterRepository;

    CheckEmployeeService(ChefRepository chefRepository, CashierRepository cashierRepository, WaiterRepository waiterRepository){
        this.chefRepository = chefRepository;
        this.cashierRepository = cashierRepository;
        this.waiterRepository = waiterRepository;
    }

    @Override
    public String findEmployeeJob(String idEmployee) {
        Chef chef = chefRepository.findByIdEmployee(idEmployee);
        Cashier cashier = cashierRepository.findByIdEmployee(idEmployee);
        Waiter waiter = waiterRepository.findByIdEmployee(idEmployee);
        if(chef != null) return "chef";
        if(cashier != null) return "cashier";
        if(waiter != null) return  "waiter";
        return null;
    }
}
