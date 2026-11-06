package com.example.restaurant_management_app.controller;

import com.example.restaurant_management_app.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menus")
public class MenuController {

    private final MenuItemService menuItemService;

    @Autowired
    public MenuController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }


}
