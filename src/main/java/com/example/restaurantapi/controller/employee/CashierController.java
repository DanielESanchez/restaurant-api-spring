package com.example.restaurantapi.controller.employee;

import com.example.restaurantapi.dao.response.ResponseOk;
import com.example.restaurantapi.model.Cashier;
import com.example.restaurantapi.services.employee.implementation.CashierService;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@EnableMongoRepositories
public class CashierController {

    private final CashierService cashierService;

    CashierController(CashierService cashierService) {
        this.cashierService = cashierService;
    }

    @GetMapping("/cashiers")
    List<Cashier> allCashier() {
        return cashierService.getAllCashiers();
    }

    @GetMapping("/cashier/get/{idEmployee}")
    Cashier findId(@PathVariable String idEmployee) {
        return cashierService.findCashierByIdEmployee(idEmployee);
    }

    @PostMapping("/cashier/new")
    ResponseEntity<ResponseOk> newCashier(@RequestBody Cashier cashier) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( cashierService.saveCashier(cashier) )
                        .build());
    }

    @PutMapping("/cashier/update")
    ResponseEntity<ResponseOk> replaceCashier(@RequestBody Cashier newCashier) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( cashierService.updateCashier(newCashier) )
                        .build());
    }

    @DeleteMapping("/cashier/delete/{idEmployee}")
    ResponseEntity<ResponseOk> deleteCashier(@PathVariable String idEmployee) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( cashierService.deleteCashier(idEmployee) )
                        .build()
        );
    }
}
