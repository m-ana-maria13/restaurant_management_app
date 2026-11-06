package com.example.restaurant_management_app.controller;

import com.example.restaurant_management_app.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;

public class StaffController {
    public final StaffService staffService;

    @Autowired
    StaffController(StaffService service){
        this.staffService = service;
    }
}
