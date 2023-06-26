package com.example.restaurantapi.controller;

import com.example.restaurantapi.model.Bill;
import com.example.restaurantapi.repository.BillRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMongoRepositories
public class BillController {
    private final BillRepository repository;

    BillController(BillRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/bills")
    List<Bill> allBill() {
        return repository.findAll();
    }

    @GetMapping("/bill/{idBill}")
    String findId(@PathVariable String idBill) {
        Bill bill = repository.findBillByIdBill(idBill);
        return (bill == null) ? "404" : bill.get_id();
    }

    @PostMapping("/bill")
    Bill newBill(@RequestBody Bill bill) {
        return repository.save(bill);
    }

    @PutMapping("/bill/{idBill}")
    Bill replaceBill(@RequestBody Bill newBill, @PathVariable String idBill) {
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
                    return repository.save(bill);
                })
                .orElseGet(() -> repository.save(newBill));
    }

    @DeleteMapping("/bill/{idBill}")
    String deleteBill(@PathVariable String idBill) {
        Bill bill = repository.findBillByIdBill(idBill);
        if(bill == null) return "Not Found";
        String _id = bill.get_id();
        repository.deleteById(_id);
        return "Completed";
    }

}
