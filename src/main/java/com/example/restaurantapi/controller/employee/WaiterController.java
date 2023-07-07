package com.example.restaurantapi.controller.employee;

import com.example.restaurantapi.dao.response.ResponseOk;
import com.example.restaurantapi.model.Waiter;

import com.example.restaurantapi.services.employee.implementation.WaiterService;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@EnableMongoRepositories
public class WaiterController {

    private final WaiterService waiterService;

    public WaiterController(WaiterService waiterService) {
        this.waiterService = waiterService;
    }


    @GetMapping("/waiters")
    List<Waiter> allWaiter() {
        return waiterService.getAllWaiters();
    }

    @GetMapping("/waiter/get/{idEmployee}")
    Waiter findId(@PathVariable String idEmployee) {
        return waiterService.findWaiterByIdEmployee(idEmployee);
    }

    @GetMapping("/waiters/available")
    List<Waiter> availableWaiters() {
        return waiterService.getAvailableWaiters();
    }

    @PostMapping("/waiter/new")
    ResponseEntity<ResponseOk> newWaiter(@RequestBody Waiter waiter) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( waiterService.saveWaiter(waiter) )
                        .build());
    }

    @PutMapping("/waiter/update/{idEmployee}")
    ResponseEntity<ResponseOk> replaceWaiter(@RequestBody Waiter newWaiter) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( waiterService.updateWaiter(newWaiter) )
                        .build());
    }

    @DeleteMapping("/waiter/delete/{idEmployee}")
    ResponseEntity<ResponseOk> deleteWaiter(@PathVariable String idEmployee) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( waiterService.deleteWaiter(idEmployee) )
                        .build());
    }
}
