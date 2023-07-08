package com.example.restaurantapi.services.restaurant.implementation;

import com.example.restaurantapi.model.restaurant.Table;
import com.example.restaurantapi.repository.TableRepository;
import com.example.restaurantapi.services.AttributeCheckerService;
import com.example.restaurantapi.services.restaurant.interfaces.ITableService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TableService implements ITableService {
    private final TableRepository tableRepository;
    private final AttributeCheckerService attributeCheckerService;

    public TableService(TableRepository tableRepository, AttributeCheckerService attributeCheckerService) {
        this.tableRepository = tableRepository;
        this.attributeCheckerService = attributeCheckerService;
    }

    @Override
    public List<Table> allTable() {
        List<Table> tableList = tableRepository.findAll();
        if (tableList.size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There are no tables to show.");
        }
        return tableRepository.findAll();
    }

    @Override
    public Table findId(Long tableNumber) {
        Table table = tableRepository.findByTableNumber(tableNumber);
        if(table == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Table not found.");
        }
        return table;
    }

    @Override
    public String newTable(Table table) {
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(table);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        return "Table saved: " + tableRepository.save(table).getTableNumber().toString();
    }

    @Override
    public String replaceTable(Table newTable) {
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(newTable);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        Table oldTable = tableRepository.findByTableNumber(newTable.getTableNumber());
        if(oldTable == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Table to update Not Found");
        }
        String _id = oldTable.get_id();
        return tableRepository.findById(_id)
                .map(table -> {
                    table.setIsEmpty(newTable.getIsEmpty());
                    tableRepository.save(table);
                    return "Table " + table.getTableNumber() + " was updated";
                })
                .orElseGet(() -> {
                    tableRepository.save(newTable);
                    return "Table " + newTable.getTableNumber() + " was saved";
                });
    }

    @Override
    public String deleteTable(Long tableNumber) {
        Table table = tableRepository.findByTableNumber(tableNumber);
        if(table == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Table to delete Not Found");
        }
        String _id = table.get_id();
        tableRepository.deleteById(_id);
        return table.getTableNumber() + " was successfully deleted.";
    }
}
