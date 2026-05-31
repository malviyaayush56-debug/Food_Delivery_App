package com.FoodApp.FoodiesApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        // Yeh static HTML file load karega
        return "forward:/index.html";
    }
}
