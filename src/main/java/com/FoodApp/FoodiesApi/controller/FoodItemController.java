package com.FoodApp.FoodiesApi.controller;

import com.FoodApp.FoodiesApi.SupabaseStorageService;
import com.FoodApp.FoodiesApi.model.FoodItem;
import com.FoodApp.FoodiesApi.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodItemController {

    private final FoodItemService foodItemService;
    private final SupabaseStorageService storageService;

    public FoodItemController(FoodItemService foodItemService, SupabaseStorageService storageService) {
        this.foodItemService = foodItemService;
        this.storageService = storageService;
    }

    // Naya food item image ke sath upload karne ke liye
    @PostMapping("/add")
    public ResponseEntity<FoodItem> createFoodItem(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("category") String category,
            @RequestParam("file") MultipartFile file) throws Exception {

        // 1. Supabase par file upload karo aur link lo
        String imageUrl = storageService.uploadFile(file);

        // 2. FoodItem object banao
        FoodItem item = new FoodItem();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setCategory(category);
        item.setImageUrl(imageUrl); // Link attach kar di

        // 3. Database mein save karo
        return ResponseEntity.ok(foodItemService.addFoodItem(item));
    }

    @GetMapping("/all")
    public ResponseEntity<List<FoodItem>> getAll() {
        return ResponseEntity.ok(foodItemService.getAllFoodItems());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<FoodItem>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(foodItemService.getFoodByCategory(category));
    }
}