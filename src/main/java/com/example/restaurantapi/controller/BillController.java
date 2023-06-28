package com.example.restaurantapi.controller;

import com.example.restaurantapi.model.Bill;
import com.example.restaurantapi.repository.BillRepository;
import com.example.restaurantapi.services.AttributeNullCheckerService;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@EnableMongoRepositories
public class BillController {
    private final BillRepository repository;
    private final AttributeNullCheckerService attributeNullCheckerService;

    BillController(BillRepository repository, AttributeNullCheckerService attributeNullCheckerService) {
        this.repository = repository;
        this.attributeNullCheckerService = attributeNullCheckerService;
    }

    @GetMapping("/bills")
    List<Bill> allBill() {
        List<Bill> billList = repository.findAll();
        if (billList.size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There are no bills to show.");
        }
        return repository.findAll();
    }

    @GetMapping("/bill/{idBill}")
    String findId(@PathVariable String idBill) {
        Bill bill = repository.findBillByIdBill(idBill);
        return (bill == null) ? "404" : bill.get_id();
    }

    @PostMapping("/bill")
    ResponseEntity newBill(@RequestBody Bill bill) {
        String messageResponseFromNullTest = attributeNullCheckerService.checkNullsInObject(bill);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        repository.save(bill);
        return new ResponseEntity<>(
                bill.getIdBill() + " was successfully saved.",
                HttpStatus.OK);
    }

    @PutMapping("/bill/{idBill}")
    ResponseEntity replaceBill(@RequestBody Bill newBill, @PathVariable String idBill) {
        String messageResponseFromNullTest = attributeNullCheckerService.checkNullsInObject(newBill);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        Bill oldBill = repository.findBillByIdBill(idBill);
        if(oldBill == null) return null;
        String _id = oldBill.get_id();
        return repository.findById(_id)
                .map(bill -> {
                    bill.setIdBill(newBill.getIdBill());
                    bill.setDateBill(newBill.getDateBill());
                    bill.setCashier(newBill.getCashier());
                    bill.setCustomer(newBill.getCustomer());
                    bill.setSubtotal(newBill.getSubtotal());
                    bill.setTable(newBill.getTable());
                    bill.setTaxesPercentage(newBill.getTaxesPercentage());
                    bill.setTaxesTotal(newBill.getTaxesTotal());
                    bill.setDiscount(newBill.getDiscount());
                    bill.setDiscountTotal(newBill.getDiscountTotal());
                    bill.setTotal(newBill.getTotal());
                    bill.setItems(newBill.getItems());
                    repository.save(bill);
                    return new ResponseEntity<>(
                            bill.getIdBill() + " was successfully updated.",
                            HttpStatus.OK);
                })
                .orElseGet(() -> {
                    repository.save(newBill);
                    return new ResponseEntity<>(
                            newBill.getIdBill() + " was saved.",
                            HttpStatus.OK);
                });
    }

    @DeleteMapping("/bill/{idBill}")
    ResponseEntity deleteBill(@PathVariable String idBill) {
        Bill bill = repository.findBillByIdBill(idBill);
        if(bill == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Menu Item Not Found");
        }
        String _id = bill.get_id();
        repository.deleteById(_id);
        return new ResponseEntity<>(
                bill.getIdBill() + " was successfully deleted.",
                HttpStatus.OK);
    }

}
