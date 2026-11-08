package com.example.restaurant_management_app.controller;

import com.example.restaurant_management_app.model.MenuItem;
import com.example.restaurant_management_app.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/menuItem")
public class MenuItemController {

    private final MenuItemService menuItemService;

    @Autowired
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }


    @GetMapping
    public String getAllMenuItems(Model model){
        model.addAttribute("menuItems", menuItemService.getAll()) ;
        return "menuItem/index";
    }


    @GetMapping("/new")
    public String showForm(Model model){
        model.addAttribute("menuItem", new MenuItem());
        return "menuItem/form";
    }

    @PostMapping
    public String saveMenuItem(MenuItem menuItem){
        menuItemService.addNewMenuItem(menuItem.getName(),menuItem.getPrice());
        return "redirect:/menuItem";
    }


    @PostMapping("/{id}/delete")
    public String deleteMenuItem(@PathVariable("id") String id){
        menuItemService.deleteMenuItem(id);
        return "redirect:/menuItem";
    }

}
