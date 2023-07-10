package com.example.restaurantapi.services.restaurant.implementation;

import com.example.restaurantapi.model.restaurant.Order;
import com.example.restaurantapi.model.user.User;
import com.example.restaurantapi.repository.OrderRepository;
import com.example.restaurantapi.repository.UserRepository;
import com.example.restaurantapi.services.AttributeCheckerService;
import com.example.restaurantapi.services.restaurant.interfaces.IOrderService;
import com.example.restaurantapi.services.security.implementation.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final AttributeCheckerService attributeCheckerService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public OrderService(AttributeCheckerService attributeCheckerService, OrderRepository orderRepository, UserRepository userRepository, JwtService jwtService) {
        this.attributeCheckerService = attributeCheckerService;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public List<Order> allOrder() {
        List<Order> orderList = orderRepository.findAll();
        if (orderList.size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There are no orders to show.");
        }
        return orderList;
    }

    @Override
    public Order findId(Long orderNumber) {
        Order order = orderRepository.findItemByProductId(orderNumber);
        if(order == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Order not found.");
        }
        return order;
    }

    @Override
    public String newOrder(Order order, String header) {
        final String jwt = header.substring(7);
        final String username = jwtService.extractUsername(jwt);
        User user = userRepository.findUserByUsername(username);
        if(user ==  null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found");
        }
        order.setUsername(username);
        String messageResponseFromNullTest = attributeCheckerService.checkNullsInObject(order);
        if(messageResponseFromNullTest != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, messageResponseFromNullTest);
        }
        orderRepository.save(order);
        return order.getOrderNumber() + " was successfully saved.";
    }

    @Override
    public String replaceOrder(Order newOrder) {
        Order oldOrder = orderRepository.findItemByProductId(newOrder.getOrderNumber());
        if(oldOrder == null) return null;
        String _id = oldOrder.get_id();
        return orderRepository.findById(_id)
                .map(order -> {
                    order.setOrderNumber(newOrder.getOrderNumber());
                    order.setOrderList(newOrder.getOrderList());
                    order.setIsCompleted(newOrder.getIsCompleted());
                    order.setTable(newOrder.getTable());
                    order.setWaiterAssigned(newOrder.getWaiterAssigned());
                    orderRepository.save(order);
                    return order.getOrderNumber() + " was successfully updated.";
                })
                .orElseGet(() -> {
                    orderRepository.save(newOrder);
                    return newOrder.getOrderNumber() + " was successfully saved.";
                });
    }

    @Override
    public String deleteOrder(Long orderNumber) {
        Order order = orderRepository.findItemByProductId(orderNumber);
        if(order == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Order Not Found");
        }
        String _id = order.get_id();
        orderRepository.deleteById(_id);
        return order.getOrderNumber() + " was successfully deleted.";
    }
}
