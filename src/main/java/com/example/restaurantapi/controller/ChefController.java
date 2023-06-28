package com.example.restaurantapi.controller;

import com.example.restaurantapi.model.Chef;
import com.example.restaurantapi.repository.ChefRepository;
import com.example.restaurantapi.services.AttributeNullCheckerService;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@EnableMongoRepositories
public class ChefController {

    private final ChefRepository repository;
    private final AttributeNullCheckerService attributeNullCheckerService;

    ChefController(AttributeNullCheckerService attributeNullCheckerService,
                   ChefRepository repository) {
        this.attributeNullCheckerService = attributeNullCheckerService;
        this.repository = repository;
    }

    @GetMapping("/chefs")
    List<Chef> allChef() {
        List<Chef> chefList = repository.findAll();
        if (chefList.size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There are no chefs to show");
        }
        return repository.findAll();
    }

    @GetMapping("/chef/{idEmployee}")
    String findId(@PathVariable String idEmployee) {
        Chef chef = repository.findByIdEmployee(idEmployee);
        return (chef == null) ? "404" : chef.get_id();
    }

    @PostMapping("/chef")
    ResponseEntity newChef(@RequestBody Chef chef) {
        String messageResponseFromNullTest = attributeNullCheckerService.checkNullsInObject(chef);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        repository.save(chef);
        return new ResponseEntity<>(
                chef.getIdEmployee() + " was successfully deleted.",
                HttpStatus.OK);
    }

    @PutMapping("/chef/{idEmployee}")
    ResponseEntity replaceChef(@RequestBody Chef newChef, @PathVariable String idEmployee) {
        String messageResponseFromNullTest = attributeNullCheckerService.checkNullsInObject(newChef);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        Chef oldChef = repository.findByIdEmployee(idEmployee);
        if(oldChef == null) return null;
        String _id = oldChef.get_id();
        return repository.findById(_id)
                .map(chef -> {
                    chef.setName(newChef.getName());
                    repository.save(chef);
                    return new ResponseEntity<>(
                            chef.getIdEmployee() + " was updated.",
                            HttpStatus.OK);
                })
                .orElseGet(() -> {
                    repository.save(newChef);
                    return new ResponseEntity<>(
                            newChef.getIdEmployee() + " was saved.",
                            HttpStatus.OK);
                });
    }

    @DeleteMapping("/chef/{idEmployee}")
    ResponseEntity deleteChef(@PathVariable String idEmployee) {
        Chef chef = repository.findByIdEmployee(idEmployee);
        if(chef == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Chef Not Found");
        }
        String _id = chef.get_id();
        repository.deleteById(_id);
        return new ResponseEntity<>(
                chef.getIsEmployee() + " was successfully deleted.",
                HttpStatus.OK);
    }
}
