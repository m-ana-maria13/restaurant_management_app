package com.example.restaurant_management_app.controller;

import com.example.restaurant_management_app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderController {
    public final OrderService orderService;

    @Autowired
    OrderController(OrderService service){
        this.orderService = service;
    }
}
