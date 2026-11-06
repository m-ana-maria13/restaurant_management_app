package com.example.restaurant_management_app.controller;


import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.model.Order;
import com.example.restaurant_management_app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    public final OrderService orderService;

    @Autowired
    OrderController(OrderService service){
        this.orderService = service;
    }
    
    @GetMapping
    public String getAllOrders(Model model){
        model.addAttribute("order",orderService.getAllOrders());
        return "order/index";
    }


    @GetMapping("/new")
    public String showForm(){
        return "order/form";
    }

//
//    @PostMapping
//    public String createOrder(@RequestParam int number, @RequestParam List<Order> orders){
//        orderService.addNewTable(number,orders);
//        return "redirect:/order";
//    }

}
