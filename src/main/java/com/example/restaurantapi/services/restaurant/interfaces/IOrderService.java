package com.example.restaurantapi.services.restaurant.interfaces;

import com.example.restaurantapi.model.restaurant.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IOrderService {
    List<Order> allOrder();
    Order findId(Long orderNumber);
    String newOrder(Order order);
    String replaceOrder(@RequestBody Order newOrder);
    String deleteOrder(@PathVariable Long orderNumber);
}
