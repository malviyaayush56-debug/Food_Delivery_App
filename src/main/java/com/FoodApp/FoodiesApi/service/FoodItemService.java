package com.FoodApp.FoodiesApi.service;

import com.FoodApp.FoodiesApi.model.FoodItem;
import com.FoodApp.FoodiesApi.repository.FoodItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoodItemService {

    private final FoodItemRepository repository;

    public FoodItemService(FoodItemRepository repository) {
        this.repository = repository;
    }

    public FoodItem addFoodItem(FoodItem foodItem) {
        return repository.save(foodItem);
    }

    public List<FoodItem> getAllFoodItems() {
        return repository.findAll();
    }

    public List<FoodItem> getFoodByCategory(String category) {
        return repository.findByCategory(category);
    }

    public FoodItem getFoodById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Food item not found!"));
    }

    public void deleteFoodItem(String id) {
        repository.deleteById(id);
    }
}
