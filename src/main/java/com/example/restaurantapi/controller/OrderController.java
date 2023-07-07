package com.example.restaurantapi.controller;

import com.example.restaurantapi.model.Order;
import com.example.restaurantapi.repository.OrderRepository;
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
public class OrderController {
    private final OrderRepository repository;
    private final AttributeCheckerService attributeCheckerService;


    OrderController(AttributeCheckerService attributeCheckerService,
                    OrderRepository repository) {
        this.attributeCheckerService = attributeCheckerService;
        this.repository = repository;
    }

    @GetMapping("/orders")
    List<Order> allOrder() {
        List<Order> orderList = repository.findAll();
        if (orderList.size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There are no orders to show.");
        }
        return orderList;
    }

    @GetMapping("/order/get/{orderNumber}")
    String findId(@PathVariable Long orderNumber) {
        Order order = repository.findItemByProductId(orderNumber);
        return (order == null) ? "404" : order.get_id();
    }

    @PostMapping("/order/new")
    ResponseEntity newOrder(@RequestBody Order order) {
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(order);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        repository.save(order);
        return new ResponseEntity<>(
                order.getOrderNumber() + " was successfully saved.",
                HttpStatus.OK);
    }

    @PutMapping("/order/update/{orderNumber}")
    ResponseEntity replaceOrder(@RequestBody Order newOrder) {
        Order oldOrder = repository.findItemByProductId(newOrder.getOrderNumber());
        if(oldOrder == null) return null;
        String _id = oldOrder.get_id();
        return repository.findById(_id)
                .map(order -> {
                    order.setOrderNumber(newOrder.getOrderNumber());
                    order.setOrderList(newOrder.getOrderList());
                    order.setIsCompleted(newOrder.getIsCompleted());
                    order.setTable(newOrder.getTable());
                    order.setWaiterAssigned(newOrder.getWaiterAssigned());
                    repository.save(order);
                    return new ResponseEntity<>(
                            order.getOrderNumber() + " was successfully updated.",
                            HttpStatus.OK);
                })
                .orElseGet(() -> {
                    repository.save(newOrder);
                    return new ResponseEntity<>(
                            newOrder.getOrderNumber() + " was successfully saved.",
                            HttpStatus.OK);
                });
    }

    @DeleteMapping("/order/delete/{orderNumber}")
    ResponseEntity deleteOrder(@PathVariable Long orderNumber) {
        Order order = repository.findItemByProductId(orderNumber);
        if(order == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Order Not Found");
        }
        String _id = order.get_id();
        repository.deleteById(_id);
        return new ResponseEntity<>(
                order.getOrderNumber() + " was successfully deleted.",
                HttpStatus.OK);
    }
}
