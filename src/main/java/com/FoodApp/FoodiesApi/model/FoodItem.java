package com.FoodApp.FoodiesApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "food_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private String category; // e.g., Burgers, Pizza, Desserts
    private String imageUrl; // Supabase ka return kiya hua URL yahan save hoga
    private boolean available = true;
}