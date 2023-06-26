package com.example.restaurantapi.controller;

import com.example.restaurantapi.model.Chef;
import com.example.restaurantapi.repository.ChefRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ChefController {

    private final ChefRepository repository;

    ChefController(ChefRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/chefs")
    List<Chef> allChef() {
        return repository.findAll();
    }

    @GetMapping("/chef/{idEmployee}")
    String findId(@PathVariable String idEmployee) {
        Chef chef = repository.findByIdEmployee(idEmployee);
        return (chef == null) ? "404" : chef.get_id();
    }

    @PostMapping("/chef")
    Chef newChef(@RequestBody Chef chef) {
        return repository.save(chef);
    }

    @PutMapping("/chef/{idEmployee}")
    Chef replaceChef(@RequestBody Chef newChef, @PathVariable String idEmployee) {
        Chef oldChef = repository.findByIdEmployee(idEmployee);
        if(oldChef == null) return null;
        String _id = oldChef.get_id();
        return repository.findById(_id)
                .map(chef -> {
                    chef.setName(newChef.getName());
                    return repository.save(chef);
                })
                .orElseGet(() -> repository.save(newChef));
    }

    @DeleteMapping("/chef/{idEmployee}")
    String deleteChef(@PathVariable String idEmployee) {
        Chef chef = repository.findByIdEmployee(idEmployee);
        if(chef == null) return "Not Found";
        String _id = chef.get_id();
        repository.deleteById(_id);
        return "Completed";
    }
}
