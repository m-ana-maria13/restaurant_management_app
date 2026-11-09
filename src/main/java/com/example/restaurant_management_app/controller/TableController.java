package com.example.restaurant_management_app.controller;

import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.model.Order;
import com.example.restaurant_management_app.model.Table;
import com.example.restaurant_management_app.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/table")
public class TableController {
    public final TableService tableService;

    @Autowired
    public TableController(TableService service){
        this.tableService = service;
    }


    @GetMapping
    public String getAllTables(Model model){
        model.addAttribute("tables", tableService.getAllTables()) ;
        return "table/index";
    }


    @GetMapping("/new")
    public String showForm(Model model){
        model.addAttribute("table", new Table());
        return "table/form";
    }


    @PostMapping
    public String createTable(Table table){
        tableService.addNewTable(table.getNumber(), new ArrayList<>());
        return "redirect:/table";
    }


    @PostMapping("/{id}/delete")
    public String deleteTable(@PathVariable("id") String id){
        tableService.deleteTable(id);
        return "redirect:/table";
    }


}
