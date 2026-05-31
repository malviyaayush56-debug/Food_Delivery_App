package com.FoodApp.FoodiesApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Date;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private String id;
    private String userId;
    private List<OrderItem> items; // Yeh tumhari OrderItem file ko use karega
    private double totalAmount;
    private String status = "PENDING";
    private Date orderDate = new Date();
}