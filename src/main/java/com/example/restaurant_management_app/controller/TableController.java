package com.example.restaurant_management_app.controller;

import com.example.restaurant_management_app.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TableController {
    public final TableService tableService;

    @Autowired
    TableController(TableService service){
        this.tableService = service;
    }

}
