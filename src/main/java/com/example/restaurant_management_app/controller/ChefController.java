package com.example.restaurant_management_app.controller;


import com.example.restaurant_management_app.model.Chef;
import com.example.restaurant_management_app.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/chef")
public class ChefController {

    private final ChefService chefService;

    @Autowired
    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }


    @GetMapping
    public String getAllServer(Model model){
        model.addAttribute("chefs",chefService.getAll()) ;
        return "chef/index";
    }

    @GetMapping("/new")
    public String showForm(Model model){
        model.addAttribute("chef", new Chef());
        return "chef/form";
    }

    @PostMapping("/{id}/delete")
    public String saveChef(Chef chef){
        chefService.saveChef(chef.getName(),chef.getSpecialization());
        return "redirect:/chef";
    }


    @PostMapping
    public String deleteChef(@PathVariable("id") String id){
       chefService.deleteChef(id);
        return "redirect:/chef";
    }



}
