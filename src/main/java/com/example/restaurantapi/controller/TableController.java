package com.example.restaurantapi.controller;

import com.example.restaurantapi.model.Table;
import com.example.restaurantapi.repository.TableRepository;
import com.example.restaurantapi.services.AttributeNullCheckerService;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@EnableMongoRepositories
public class TableController {

    private final TableRepository repository;
    private final AttributeNullCheckerService attributeNullCheckerService;

    TableController(AttributeNullCheckerService attributeNullCheckerService,
                    TableRepository repository) {
        this.attributeNullCheckerService = attributeNullCheckerService;
        this.repository = repository;
    }

    @GetMapping("/tables")
    List<Table> allTable() {
        List<Table> tableList = repository.findAll();
        if (tableList.size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There are no tables to show, please add a new item to the menu");
        }
        return repository.findAll();
    }

    @GetMapping("/table/{tableNumber}")
    String findId(@PathVariable Long tableNumber) {
        Table table = repository.findByTableNumber(tableNumber);
        return (table == null) ? "404" : table.get_id();
    }

    @PostMapping("/table")
    Table newTable(@RequestBody Table table) {
        String messageResponseFromNullTest = attributeNullCheckerService.checkNullsInObject(table);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        return repository.save(table);
    }

    @PutMapping("/table/{tableNumber}")
    Table replaceTable(@RequestBody Table newTable, @PathVariable Long tableNumber) {
        String messageResponseFromNullTest = attributeNullCheckerService.checkNullsInObject(newTable);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        Table oldTable = repository.findByTableNumber(tableNumber);
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

    @DeleteMapping("/table/{tableNumber}")
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
