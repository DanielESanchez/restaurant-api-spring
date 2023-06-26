package com.example.restaurantapi.controller;

import com.example.restaurantapi.model.Cashier;
import com.example.restaurantapi.repository.CashierRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMongoRepositories
public class CashierController {

    private final CashierRepository repository;

    CashierController(CashierRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cashiers")
    List<Cashier> allCashier() {
        return repository.findAll();
    }

    @GetMapping("/cashier/{idEmployee}")
    String findId(@PathVariable String idEmployee) {
        Cashier cashier = repository.findByIdEmployee(idEmployee);
        return (cashier == null) ? "404" : cashier.get_id();
    }

    @PostMapping("/cashier")
    Cashier newCashier(@RequestBody Cashier cashier) {
        return repository.save(cashier);
    }

    @PutMapping("/cashier/{idEmployee}")
    Cashier replaceCashier(@RequestBody Cashier newCashier, @PathVariable String idEmployee) {
        Cashier oldCashier = repository.findByIdEmployee(idEmployee);
        if(oldCashier == null) return null;
        String _id = oldCashier.get_id();
        return repository.findById(_id)
                .map(cashier -> {
                    cashier.setName(newCashier.getName());
                    return repository.save(cashier);
                })
                .orElseGet(() -> repository.save(newCashier));
    }

    @DeleteMapping("/cashier/{idEmployee}")
    String deleteCashier(@PathVariable String idEmployee) {
        Cashier cashier = repository.findByIdEmployee(idEmployee);
        if(cashier == null) return "Not Found";
        String _id = cashier.get_id();
        repository.deleteById(_id);
        return "Completed";
    }
}
