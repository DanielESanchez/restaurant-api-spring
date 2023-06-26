package com.example.restaurantapi.controller;

import com.example.restaurantapi.model.Order;
import com.example.restaurantapi.repository.OrderRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMongoRepositories
public class OrderController {
    private final OrderRepository repository;

    OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/orders")
    List<Order> allOrder() {
        return repository.findAll();
    }

    @GetMapping("/order/{orderNumber}")
    String findId(@PathVariable long orderNumber) {
        Order order = repository.findItemByProductId(orderNumber);
        return (order == null) ? "404" : order.get_id();
    }

    @PostMapping("/order")
    Order newOrder(@RequestBody Order order) {
        return repository.save(order);
    }

    @PutMapping("/order/{orderNumber}")
    Order replaceOrder(@RequestBody Order newOrder, @PathVariable long orderNumber) {
        Order oldOrder = repository.findItemByProductId(orderNumber);
        if(oldOrder == null) return null;
        String _id = oldOrder.get_id();
        return repository.findById(_id)
                .map(order -> {
                    order.setOrderNumber(newOrder.getOrderNumber());
                    order.setOrderList(newOrder.getOrderList());
                    order.setCompleted(newOrder.isCompleted());
                    order.setTable(newOrder.getTable());
                    order.setWaiterAssigned(newOrder.getWaiterAssigned());
                    return repository.save(order);
                })
                .orElseGet(() -> repository.save(newOrder));
    }

    @DeleteMapping("/order/{orderNumber}")
    String deleteOrder(@PathVariable long orderNumber) {
        Order order = repository.findItemByProductId(orderNumber);
        if(order == null) return "Not Found";
        String _id = order.get_id();
        repository.deleteById(_id);
        return "Completed";
    }
}
