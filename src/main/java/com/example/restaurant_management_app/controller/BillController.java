package com.example.restaurant_management_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bills")
public class BillController {
    private final BillController billController;

    @Autowired
    public BillController(BillController billController) {
        this.billController = billController;
    }


}
