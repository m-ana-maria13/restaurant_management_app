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
    public OrderController(OrderService service) {
        this.orderService = service;
    }

    @GetMapping
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "order/index";
    }


    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("order", new Order());
        return "order/form";
    }

    @PostMapping
    public String saveOrder(Order order){
        orderService.createOrder(order.getCustomerId(),order.getTableId(), order.getTakeAway());
        return "redirect:/order";
    }


    @PostMapping("/{id}/delete")
    public String deleteOrder(@PathVariable("id") String id){
            orderService.deleteOrder(id);
        return "redirect:/order";
    }

}