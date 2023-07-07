package com.example.restaurantapi.controller;

import com.example.restaurantapi.model.Chef;
import com.example.restaurantapi.repository.ChefRepository;
import com.example.restaurantapi.services.employee.implementation.ChefService;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMongoRepositories
public class ChefController {

    private final ChefRepository repository;
    private final ChefService chefService;

    ChefController(ChefRepository repository, ChefService chefService) {
        this.repository = repository;
        this.chefService = chefService;
    }

    @GetMapping("/chefs")
    List<Chef> allChef() {
        return chefService.getAllChefs();
    }

    @GetMapping("/chef/{idEmployee}")
    Chef findId(@PathVariable String idEmployee) {
        return chefService.findChefByIdEmployee(idEmployee);
    }

    @GetMapping("/chefs/available")
    List<Chef> availableChefs() {
        return chefService.getAvailableChefs();
    }

    @PostMapping("/chef/new")
    ResponseEntity<String> newChef(@RequestBody Chef chef) {
        return ResponseEntity.ok(chefService.saveChef(chef));
    }

    @PutMapping("/chef/update")
    ResponseEntity<String> updateChef(@RequestBody Chef newChef) {
        return ResponseEntity.ok(chefService.updateChef(newChef));
    }

    @DeleteMapping("/chef/{idEmployee}")
    ResponseEntity<String> deleteChef(@PathVariable String idEmployee) {
        return ResponseEntity.ok(chefService.deleteChef(idEmployee));
    }
}
