package com.FoodApp.FoodiesApi.dto;

public record LocationUpdate(
        String orderId,
        String deliveryBoyId,
        double latitude,
        double longitude
) {}