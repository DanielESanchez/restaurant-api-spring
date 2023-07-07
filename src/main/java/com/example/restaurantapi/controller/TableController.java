package com.example.restaurantapi.controller;

import com.example.restaurantapi.model.Table;
import com.example.restaurantapi.repository.TableRepository;
import com.example.restaurantapi.services.AttributeCheckerService;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@EnableMongoRepositories
public class TableController {

    private final TableRepository repository;
    private final AttributeCheckerService attributeCheckerService;

    TableController(AttributeCheckerService attributeCheckerService,
                    TableRepository repository) {
        this.attributeCheckerService = attributeCheckerService;
        this.repository = repository;
    }

    @GetMapping("/tables")
    List<Table> allTable() {
        List<Table> tableList = repository.findAll();
        if (tableList.size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There are no tables to show.");
        }
        return repository.findAll();
    }

    @GetMapping("/table/get/{tableNumber}")
    String findId(@PathVariable Long tableNumber) {
        Table table = repository.findByTableNumber(tableNumber);
        return (table == null) ? "404" : table.get_id();
    }

    @PostMapping("/table/new")
    ResponseEntity<String> newTable(@RequestBody Table table) {
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(table);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        return ResponseEntity.ok("Table saved: " + repository.save(table).getTableNumber().toString());
    }

    @PutMapping("/table/update/{tableNumber}")
    Table replaceTable(@RequestBody Table newTable) {
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(newTable);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        Table oldTable = repository.findByTableNumber(newTable.getTableNumber());
        if(oldTable == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Table to update Not Found");
        }
        String _id = oldTable.get_id();
        return repository.findById(_id)
                .map(table -> {
                    table.setTableNumber(newTable.getTableNumber());
                    table.setIsEmpty(newTable.getIsEmpty());
                    return repository.save(table);
                })
                .orElseGet(() -> repository.save(newTable));
    }

    @DeleteMapping("/table/delete/{tableNumber}")
    ResponseEntity deleteTable(@PathVariable Long tableNumber) {
        Table table = repository.findByTableNumber(tableNumber);
        if(table == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Table to delete Not Found");
        }
        String _id = table.get_id();
        repository.deleteById(_id);
        return new ResponseEntity<>(
                table.getTableNumber() + " was successfully deleted.",
                HttpStatus.OK);
    }
}
