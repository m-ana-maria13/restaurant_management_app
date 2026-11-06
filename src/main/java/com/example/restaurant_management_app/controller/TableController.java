package com.example.restaurant_management_app.controller;

import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.model.Order;
import com.example.restaurant_management_app.model.Table;
import com.example.restaurant_management_app.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/table")
public class TableController {
    public final TableService tableService;

    @Autowired
    TableController(TableService service){
        this.tableService = service;
    }


    @GetMapping
    public String getAllTables(Model model){
        model.addAttribute("table", tableService.getAllTables()) ;
        return "table/index";
    }


    @GetMapping("/new")
    public String showForm(){
        return "table/form";
    }


    @PostMapping
    public String createTable(@RequestParam int number, @RequestParam List<Order> orders){
        tableService.addNewTable(number,orders);
        return "redirect:/table";
    }

}
