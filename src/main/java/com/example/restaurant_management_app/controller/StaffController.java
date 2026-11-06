package com.example.restaurant_management_app.controller;

import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.model.Staff;
import com.example.restaurant_management_app.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    public final StaffService staffService;

    @Autowired
    StaffController(StaffService service){
        this.staffService = service;
    }


    @GetMapping
    public String getAllStaff(Model model){
         model.addAttribute("staff",staffService.getAll()) ;
         return "staff/index";
    }


    @GetMapping("/new")
    public String showForm(){
        return "staff/form";
    }

}
