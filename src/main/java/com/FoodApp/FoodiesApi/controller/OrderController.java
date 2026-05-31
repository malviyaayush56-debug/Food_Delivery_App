//package com.FoodApp.FoodiesApi.controller;
//
//import com.FoodApp.FoodiesApi.model.Order;
//import com.FoodApp.FoodiesApi.service.OrderService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/orders")
//@CrossOrigin(origins = "*")
//public class OrderController {
//
//    private final OrderService orderService;
//
//    public OrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    // 1. Order Place karne ke liye
//    @PostMapping("/place")
//    public ResponseEntity<?> placeOrder(@RequestBody Order order) {
//        try {
//            System.out.println("===> Incoming Order Payload UserID: " + order.getUserId());
//            Order savedOrder = orderService.placeOrder(order);
//            return ResponseEntity.ok(savedOrder);
//        } catch (Exception e) {
//            System.err.println("!!! ERROR PLACING ORDER: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Backend Error: " + e.getMessage());
//        }
//    }
//
//    // 2. Particular User ke saare orders nikalne ke liye
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<Order>> getUserOrders(@PathVariable String userId) {
//        try {
//            List<Order> orders = orderService.getOrdersByUser(userId);
//            return ResponseEntity.ok(orders);
//        } catch (Exception e) {
//            System.err.println("!!! ERROR GETTING USER ORDERS: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    // 3. Order ka status update karne ke liye (e.g. PENDING se DELIVERED)
//    @PutMapping("/status/{orderId}")
//    public ResponseEntity<?> updateStatus(@PathVariable String orderId, @RequestParam String status) {
//        try {
//            Order updatedOrder = orderService.updateOrderStatus(orderId, status);
//            return ResponseEntity.ok(updatedOrder);
//        } catch (Exception e) {
//            System.err.println("!!! ERROR UPDATING ORDER STATUS: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Update Failed: " + e.getMessage());
//        }
//    }
//}




package com.FoodApp.FoodiesApi.controller;

import com.FoodApp.FoodiesApi.model.Order;
import com.FoodApp.FoodiesApi.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*") // Frontend requests ko allow karne ke liye
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 1. Order Place karne ke liye (With Testing Logs)
    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody Order order) {
        try {
            // Frontend se data aaya ya nahi, yeh check karne ke liye
            System.out.println("\n=========================================");
            System.out.println("===> RECEIVED FRONTEND DATA!");
            System.out.println("===> UserID: " + order.getUserId());
            System.out.println("===> Total Amount: " + order.getTotalAmount());
            System.out.println("=========================================");

            // Data ko MongoDB me save karne ke liye service call ki
            Order savedOrder = orderService.placeOrder(order);

            // Agar MongoDB ne save karke unique ID de di, toh yeh print hoga
            System.out.println("===> SUCCESS: DATA SAVED IN MONGO!");
            System.out.println("===> MONGO GENERATED ID: " + savedOrder.getId());
            System.out.println("=========================================\n");

            return ResponseEntity.ok(savedOrder);

        } catch (Exception e) {
            // Agar database me save karte waqt koi galti hui, toh poori detail yahan print hogi
            System.err.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.err.println("!!! ERROR OCCURRED WHILE PLACING ORDER !!!");
            System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            e.printStackTrace(); // Yeh terminal me laal color me poora error khol dega
            System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Backend Error: " + e.getMessage());
        }
    }

    // 2. Particular User ke saare orders nikalne ke liye
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable String userId) {
        try {
            List<Order> orders = orderService.getOrdersByUser(userId);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            System.err.println("!!! ERROR GETTING USER ORDERS: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 3. Order ka status update karne ke liye (e.g. PENDING se DELIVERED)
    @PutMapping("/status/{orderId}")
    public ResponseEntity<?> updateStatus(@PathVariable String orderId, @RequestParam String status) {
        try {
            Order updatedOrder = orderService.updateOrderStatus(orderId, status);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            System.err.println("!!! ERROR UPDATING ORDER STATUS: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Update Failed: " + e.getMessage());
        }
    }
}