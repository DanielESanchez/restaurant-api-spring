package com.example.restaurantapi.controller;

import com.example.restaurantapi.model.Waiter;
import com.example.restaurantapi.repository.WaiterRepository;
import com.example.restaurantapi.services.AttributeNullCheckerService;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@EnableMongoRepositories
public class WaiterController {

    private final WaiterRepository repository;
    private final AttributeNullCheckerService attributeNullCheckerService;

    WaiterController(AttributeNullCheckerService attributeNullCheckerService,
                     WaiterRepository repository) {
        this.attributeNullCheckerService = attributeNullCheckerService;
        this.repository = repository;
    }

    @GetMapping("/waiters")
    List<Waiter> allWaiter() {
        List<Waiter> waiterList = repository.findAll();
        if (waiterList.size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There are no waiters to show");
        }
        return waiterList;
    }

    @GetMapping("/waiter/{idEmployee}")
    String findId(@PathVariable String idEmployee) {
        Waiter waiter = repository.findByIdEmployee(idEmployee);
        return (waiter == null) ? "404" : waiter.get_id();
    }

    @PostMapping("/waiter")
    Waiter newWaiter(@RequestBody Waiter waiter) {
        String messageResponseFromNullTest = attributeNullCheckerService.checkNullsInObject(waiter);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        return repository.save(waiter);
    }

    @PutMapping("/waiter/{idEmployee}")
    Waiter replaceWaiter(@RequestBody Waiter newWaiter, @PathVariable String idEmployee) {
        String messageResponseFromNullTest = attributeNullCheckerService.checkNullsInObject(newWaiter);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        Waiter oldWaiter = repository.findByIdEmployee(idEmployee);
        if(oldWaiter == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Waiter Not Found");
        }
        String _id = oldWaiter.get_id();
        return repository.findById(_id)
                .map(waiter -> {
                    waiter.setName(newWaiter.getName());
                    return repository.save(waiter);
                })
                .orElseGet(() -> repository.save(newWaiter));
    }

    @DeleteMapping("/waiter/{idEmployee}")
    ResponseEntity deleteWaiter(@PathVariable String idEmployee) {
        Waiter waiter = repository.findByIdEmployee(idEmployee);
        if(waiter == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Waiter Not Found");
        }
        String _id = waiter.get_id();
        repository.deleteById(_id);
        return new ResponseEntity<>(
                waiter.getIdEmployee() + " was successfully deleted.",
                HttpStatus.OK);
    }
}
