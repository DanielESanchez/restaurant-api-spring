package com.example.restaurantapi.controller.employee;

import com.example.restaurantapi.dao.response.ResponseOk;
import com.example.restaurantapi.model.person.Chef;
import com.example.restaurantapi.services.employee.implementation.ChefService;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@EnableMongoRepositories
public class ChefController {

    private final ChefService chefService;

    ChefController(ChefService chefService) {
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
    ResponseEntity<ResponseOk> newChef(@RequestBody Chef chef) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( chefService.saveChef(chef) )
                        .build());
    }

    @PutMapping("/chef/update")
    ResponseEntity<ResponseOk> updateChef(@RequestBody Chef newChef) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( chefService.updateChef(newChef) )
                        .build());
    }

    @DeleteMapping("/chef/{idEmployee}")
    ResponseEntity<ResponseOk> deleteChef(@PathVariable String idEmployee) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( chefService.deleteChef(idEmployee) )
                        .build());
    }
}
