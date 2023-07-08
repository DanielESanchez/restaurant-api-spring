package com.example.restaurantapi.controller.restaurant;

import com.example.restaurantapi.dao.response.ResponseOk;
import com.example.restaurantapi.model.restaurant.Order;
import com.example.restaurantapi.services.restaurant.implementation.OrderService;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@EnableMongoRepositories
public class OrderController {
    private final OrderService orderService;


    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    List<Order> allOrder() {
        return orderService.allOrder();
    }

    @GetMapping("/order/get/{orderNumber}")
    Order findId(@PathVariable Long orderNumber) {
        return orderService.findId(orderNumber);
    }

    @PostMapping("/order/new")
    ResponseEntity<ResponseOk> newOrder(@RequestBody Order order) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( orderService.newOrder(order) )
                        .build());
    }

    @PutMapping("/order/update/{orderNumber}")
    ResponseEntity<ResponseOk> replaceOrder(@RequestBody Order newOrder) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( orderService.replaceOrder(newOrder) )
                        .build());
    }

    @DeleteMapping("/order/delete/{orderNumber}")
    ResponseEntity<ResponseOk> deleteOrder(@PathVariable Long orderNumber) {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( orderService.deleteOrder(orderNumber) )
                        .build());
    }
}
