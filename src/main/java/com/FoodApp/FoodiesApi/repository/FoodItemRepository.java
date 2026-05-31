package com.FoodApp.FoodiesApi.repository;

import com.FoodApp.FoodiesApi.model.FoodItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface FoodItemRepository extends MongoRepository<FoodItem, String> {
    List<FoodItem> findByCategory(String category); // Category wise filter karne ke liye
}