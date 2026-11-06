package com.example.restaurant_management_app.controller;

import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController( BillService billService) {
        this.billService = billService;

    }

    @GetMapping
    public String getAllBills(Model model){
        model.addAttribute("bill", billService.getAllBills()) ;
        return "bill/index";
    }


    @GetMapping("/new")
    public String showForm(){
        return "bill/form";
    }


}
