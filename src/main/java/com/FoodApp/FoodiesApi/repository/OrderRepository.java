package com.FoodApp.FoodiesApi.repository;

import com.FoodApp.FoodiesApi.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUserId(String userId); // Yeh method hona zaroori hai!
}