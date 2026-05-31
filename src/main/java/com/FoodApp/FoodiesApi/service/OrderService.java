package com.FoodApp.FoodiesApi.service;

import com.FoodApp.FoodiesApi.model.Order;
import com.FoodApp.FoodiesApi.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    // 1. Order place karne ke liye method
    public Order placeOrder(Order order) {
        return repository.save(order);
    }

    // 2. User ke orders nikalne ke liye method (Controller ise hi dhoondh raha hai)
    public List<Order> getOrdersByUser(String userId) {
        return repository.findByUserId(userId);
    }

    // 3. Saare orders nikalne ke liye
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    // 4. Status update karne ke liye (Controller ise bhi dhoondh raha hai)
    public Order updateOrderStatus(String orderId, String status) {
        Order order = repository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        order.setStatus(status);
        return repository.save(order);
    }
}