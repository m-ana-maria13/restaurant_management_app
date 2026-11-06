package com.example.restaurant_management_app.controller;

import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.model.MenuItem;
import com.example.restaurant_management_app.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menuItems")
public class MenuController {

    private final MenuItemService menuItemService;

    @Autowired
    public MenuController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }


    @GetMapping
    public String getAllMenuItems(Model model){
        model.addAttribute("menu", menuItemService.getAll()) ;
        return "menu/index";
    }


    @GetMapping("/new")
    public String showForm(){
        return "menu/form";
    }

}
