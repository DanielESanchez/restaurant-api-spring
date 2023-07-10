package com.example.restaurantapi.controller.restaurant;

import com.example.restaurantapi.dao.response.ResponseOk;
import com.example.restaurantapi.model.restaurant.Table;
import com.example.restaurantapi.services.restaurant.implementation.SequenceGeneratorService;
import com.example.restaurantapi.services.restaurant.implementation.TableService;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@EnableMongoRepositories
@RequestMapping("api")
public class TableController {

    private final TableService tableService;
    private final SequenceGeneratorService sequenceGeneratorService;

    public TableController(TableService tableService, SequenceGeneratorService sequenceGeneratorService) {
        this.tableService = tableService;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @GetMapping("/tables")
    List<Table> allTable() {
        return tableService.allTable();
    }

    @GetMapping("/table/get/{tableNumber}")
    Table findId(@PathVariable Long tableNumber) {
        System.out.println(tableNumber);
        return tableService.findId(tableNumber);
    }

    @PostMapping("/table/new")
    ResponseEntity<ResponseOk> newTable() {
        Table table = Table.builder().tableNumber(sequenceGeneratorService.generateSequence(Table.SEQUENCE_NAME)).isEmpty(true).build();
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( tableService.newTable(table) )
                        .build());
    }

    @PutMapping("/table/update/{tableNumber}")
    ResponseEntity<ResponseOk> replaceTable(@RequestBody Table newTable) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( tableService.replaceTable(newTable) )
                        .build());
    }

    @DeleteMapping("/table/delete/{tableNumber}")
    ResponseEntity<ResponseOk> deleteTable(@PathVariable Long tableNumber) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( tableService.deleteTable(tableNumber) )
                        .build());
    }
}
