package com.example.restaurant_management_app.controller;

import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.model.Customer;
import com.example.restaurant_management_app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    public String getAllCustomers(Model model){
        model.addAttribute("customers",customerService.getAllCustomers()) ;
        return "customer/index";
    }


    @GetMapping("/new")
    public String showForm(Model model){
        model.addAttribute("customer",new Customer()) ;
        return "customer/form";
    }

    @PostMapping
    public String saveCustomer(Customer customer){
        customerService.registerCustomer(customer);
        return "redirect:/customer";
    }


    @PostMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable("id") String id){
        customerService.deleteCustomer(id);
        return "redirect:/customer";
    }
}
