package com.example.restaurantapi.controller;

import com.example.restaurantapi.model.Waiter;
import com.example.restaurantapi.repository.WaiterRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMongoRepositories
public class WaiterController {

    private final WaiterRepository repository;

    WaiterController(WaiterRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/waiters")
    List<Waiter> allWaiter() {
        return repository.findAll();
    }

    @GetMapping("/waiter/{idEmployee}")
    String findId(@PathVariable String idEmployee) {
        Waiter waiter = repository.findByIdEmployee(idEmployee);
        return (waiter == null) ? "404" : waiter.get_id();
    }

    @PostMapping("/waiter")
    Waiter newWaiter(@RequestBody Waiter waiter) {
        return repository.save(waiter);
    }

    @PutMapping("/waiter/{idEmployee}")
    Waiter replaceWaiter(@RequestBody Waiter newWaiter, @PathVariable String idEmployee) {
        Waiter oldWaiter = repository.findByIdEmployee(idEmployee);
        if(oldWaiter == null) return null;
        String _id = oldWaiter.get_id();
        return repository.findById(_id)
                .map(waiter -> {
                    waiter.setName(newWaiter.getName());
                    return repository.save(waiter);
                })
                .orElseGet(() -> repository.save(newWaiter));
    }

    @DeleteMapping("/waiter/{idEmployee}")
    String deleteWaiter(@PathVariable String idEmployee) {
        Waiter waiter = repository.findByIdEmployee(idEmployee);
        if(waiter == null) return "Not Found";
        String _id = waiter.get_id();
        repository.deleteById(_id);
        return "Completed";
    }
}
