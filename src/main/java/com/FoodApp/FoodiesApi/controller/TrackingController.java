package com.FoodApp.FoodiesApi.controller;

import com.FoodApp.FoodiesApi.dto.LocationUpdate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class TrackingController {

    private final SimpMessagingTemplate messagingTemplate;

    // Spring boot automatically is template ko inject kar dega messages bhejne ke liye
    public TrackingController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Delivery boy is endpoint par location send karega: /app/share-location
    @MessageMapping("/share-location")
    public void shareLocation(@Payload LocationUpdate location) {

        // Dynamic topic path: /topic/order/{orderId}
        String destination = "/topic/order/" + location.orderId();

        // Customer ko real-time data push ho jayega
        messagingTemplate.convertAndSend(destination, location);

        System.out.println("Order " + location.orderId() + " ki location updated: Lat: " + location.latitude() + ", Lng: " + location.longitude());
    }
}