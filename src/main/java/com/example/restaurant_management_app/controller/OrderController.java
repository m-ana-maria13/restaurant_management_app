package com.example.restaurant_management_app.controller;


import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.model.MenuItem;
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


    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable String id, Model model) {
        Order order= orderService.getOrderById(id);

        model.addAttribute("order", order);

        return "order/details";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Order order= orderService.getOrderById(id);
        model.addAttribute("order", order);

        return "order/form";
    }


    @PostMapping("/edit")
    public String updateBill(@ModelAttribute  Order order) {
        orderService.update(order);

        return "redirect:/order";
    }


    @PostMapping("/{id}/addOrderLine")
    public String addOrderLine(@ModelAttribute  Order order, @PathVariable("id") String menuItemId, @PathVariable("quantity") Double quantity){
        orderService.addOrderLine(order.getId(),menuItemId,quantity);
        return "redirect:/order";
    }
}


