package com.example.restaurantapi.controller;

import com.example.restaurantapi.model.Table;
import com.example.restaurantapi.repository.TableRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMongoRepositories
public class TableController {

    private final TableRepository repository;

    TableController(TableRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/tables")
    List<Table> allTable() {
        return repository.findAll();
    }

    @GetMapping("/table/{tableNumber}")
    String findId(@PathVariable String tableNumber) {
        Table table = repository.findByTableNumber(tableNumber);
        return (table == null) ? "404" : table.get_id();
    }

    @PostMapping("/table")
    Table newTable(@RequestBody Table table) {
        return repository.save(table);
    }

    @PutMapping("/table/{tableNumber}")
    Table replaceTable(@RequestBody Table newTable, @PathVariable String tableNumber) {
        Table oldTable = repository.findByTableNumber(tableNumber);
        if(oldTable == null) return null;
        String _id = oldTable.get_id();
        return repository.findById(_id)
                .map(table -> {
                    table.setTableNumber(newTable.getTableNumber());
                    table.setEmpty(newTable.getIsEmpty());
                    return repository.save(table);
                })
                .orElseGet(() -> repository.save(newTable));
    }

    @DeleteMapping("/table/{tableNumber}")
    String deleteTable(@PathVariable String tableNumber) {
        Table table = repository.findByTableNumber(tableNumber);
        if(table == null) return "Not Found";
        String _id = table.get_id();
        repository.deleteById(_id);
        return "Completed";
    }
}
