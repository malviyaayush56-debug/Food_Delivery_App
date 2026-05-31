package com.FoodApp.FoodiesApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private String foodItemId;
    private String name;
    private int quantity;
    private double price;
}