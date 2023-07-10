package com.example.restaurantapi.services.employee.implementation;

import com.example.restaurantapi.model.person.Cashier;
import com.example.restaurantapi.model.person.Chef;
import com.example.restaurantapi.model.person.Waiter;
import com.example.restaurantapi.repository.CashierRepository;
import com.example.restaurantapi.repository.ChefRepository;
import com.example.restaurantapi.repository.WaiterRepository;
import com.example.restaurantapi.services.employee.interfaces.IEmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService implements IEmployeeService {
    private final ChefRepository chefRepository;
    private final CashierRepository cashierRepository;
    private final WaiterRepository waiterRepository;

    EmployeeService(ChefRepository chefRepository, CashierRepository cashierRepository, WaiterRepository waiterRepository){
        this.chefRepository = chefRepository;
        this.cashierRepository = cashierRepository;
        this.waiterRepository = waiterRepository;
    }

    @Override
    public String findEmployeeJob(String idEmployee) {
        Chef chef = chefRepository.findByIdEmployee(idEmployee);
        Cashier cashier = cashierRepository.findByIdEmployee(idEmployee);
        Waiter waiter = waiterRepository.findByIdEmployee(idEmployee);
        if(chef == null || cashier == null || waiter == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not Found");
        }
        if(chef != null) return chef.getJob();
        if(cashier != null) return cashier.getJob();
        return waiter.getJob();
    }

}
