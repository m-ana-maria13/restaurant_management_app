package com.example.restaurant_management_app.controller;

import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController( BillService billService) {
        this.billService = billService;

    }

    @GetMapping
    public String getAllBills(Model model){
        model.addAttribute("bills", billService.getAllBills()) ;
        return "bill/index";
    }


    @GetMapping("/new")
    public String showForm(Model model){
        model.addAttribute("bill", new Bill()) ;
        return "bill/form";
    }

    @PostMapping
    public String saveBill( Bill bill){
        billService.generateBill(bill.getOrderId());
        return "redirect:/bill";
    }

    @PostMapping("/{id}/delete")
    public String deleteBill(@PathVariable("id") String id){
            billService.deleteBill(id);
        return "redirect:/bill";
    }


}
