package com.example.restaurant_management_app.controller;

import com.example.restaurant_management_app.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff")
public class StaffController {
    public final StaffService staffService;

    @Autowired
    StaffController(StaffService service){
        this.staffService = service;
    }
}
