package com.example.restaurantapi.controller;

import com.example.restaurantapi.model.Cashier;
import com.example.restaurantapi.repository.CashierRepository;
import com.example.restaurantapi.services.implementation.AttributeCheckerService;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@EnableMongoRepositories
public class CashierController {

    private final CashierRepository repository;
    private final AttributeCheckerService attributeCheckerService;

    CashierController(AttributeCheckerService attributeCheckerService,
                      CashierRepository repository) {
        this.attributeCheckerService = attributeCheckerService;
        this.repository = repository;
    }

    @GetMapping("/cashiers")
    List<Cashier> allCashier() {
        List<Cashier> cashierList = repository.findAll();
        if (cashierList.size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There are no cashiers to show.");
        }
        return cashierList;
    }

    @GetMapping("/cashier/{idEmployee}")
    String findId(@PathVariable String idEmployee) {
        Cashier cashier = repository.findByIdEmployee(idEmployee);
        return (cashier == null) ? "404" : cashier.get_id();
    }

    @PostMapping("/cashier")
    ResponseEntity newCashier(@RequestBody Cashier cashier) {
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(cashier);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        return new ResponseEntity<>(
                cashier.getFullName() + " was successfully saved.",
                HttpStatus.OK);
    }

    @PutMapping("/cashier/{idEmployee}")
    ResponseEntity replaceCashier(@RequestBody Cashier newCashier, @PathVariable String idEmployee) {
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(newCashier);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        Cashier oldCashier = repository.findByIdEmployee(idEmployee);
        if(oldCashier == null) return null;
        String _id = oldCashier.get_id();
         return repository.findById(_id)
                .map(cashier -> {
                    cashier.setName(newCashier.getName());
                    repository.save(cashier);
                    return new ResponseEntity<>(
                            cashier.getIdEmployee() + " was successfully updated.",
                            HttpStatus.OK);
                })
                .orElseGet(() -> {
                    repository.save(newCashier);
                    return new ResponseEntity<>(
                            newCashier.getIdEmployee() + " was successfully saved.",
                            HttpStatus.OK);
                });
    }

    @DeleteMapping("/cashier/{idEmployee}")
    ResponseEntity deleteCashier(@PathVariable String idEmployee) {
        Cashier cashier = repository.findByIdEmployee(idEmployee);
        if(cashier == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cashier does not exists in the database");
        }
        String _id = cashier.get_id();
        repository.deleteById(_id);
        return new ResponseEntity<>(
                cashier.getIdEmployee() + " was successfully deleted.",
                HttpStatus.OK);
    }
}
