package com.example.restaurantapi.controller.restaurant;

import com.example.restaurantapi.dao.response.ResponseOk;
import com.example.restaurantapi.model.restaurant.Bill;
import com.example.restaurantapi.services.restaurant.implementation.BillService;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@EnableMongoRepositories
public class BillController {

    private final BillService billService;

    BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/bills")
    List<Bill> allBill() {
        return billService.allBill();
    }

    @GetMapping("/bill/get/{idBill}")
    Bill findId(@PathVariable String idBill) {
        return billService.findById(idBill);
    }

    @PostMapping("/bill/new")
    ResponseEntity<ResponseOk> newBill(@RequestBody Bill bill) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( billService.newBill(bill) )
                        .build());
    }

    @PutMapping("/bill/update/{idBill}")
    ResponseEntity<ResponseOk> replaceBill(@RequestBody Bill newBill) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( billService.updateBill(newBill) )
                        .build());
    }

    @DeleteMapping("/bill/delete/{idBill}")
    ResponseEntity<ResponseOk> deleteBill(@PathVariable String idBill) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( billService.deleteBill(idBill) )
                        .build());
    }

}
