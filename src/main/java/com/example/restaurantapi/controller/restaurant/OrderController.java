package com.example.restaurantapi.controller.restaurant;

import com.example.restaurantapi.dao.response.ResponseOk;
import com.example.restaurantapi.model.restaurant.Order;
import com.example.restaurantapi.services.restaurant.implementation.OrderService;

import com.example.restaurantapi.services.restaurant.implementation.SequenceGeneratorService;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@EnableMongoRepositories
@RequestMapping("api")
public class OrderController {
    private final OrderService orderService;
    private final SequenceGeneratorService sequenceGeneratorService;


    OrderController(OrderService orderService, SequenceGeneratorService sequenceGeneratorService) {
        this.orderService = orderService;
        this.sequenceGeneratorService = sequenceGeneratorService;
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
    ResponseEntity<ResponseOk> newOrder(@RequestBody Order order, @RequestHeader("Authorization") String header) {
        order.setOrderNumber(sequenceGeneratorService.generateSequence(Order.SEQUENCE_NAME));
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response( orderService.newOrder(order, header) )
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
