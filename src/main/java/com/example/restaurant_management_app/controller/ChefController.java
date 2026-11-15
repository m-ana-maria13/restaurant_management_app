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

    @PostMapping
    public String saveChef(Chef chef){
        chefService.saveChef(chef.getName(), chef.getSpecialization());
        return "redirect:/chef";
    }

    @PostMapping("/{id}/delete")
    public String deleteChef(@PathVariable("id") String id){
        chefService.deleteChef(id);
        return "redirect:/chef";
    }


    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable String id, Model model) {
        Chef chef= chefService.getById(id);

        model.addAttribute("chef", chef);

        return "chef/details";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Chef chef= chefService.getById(id);

        model.addAttribute("chef", chef);

        return "chef/form";
    }


    @PostMapping("/edit")
    public String updateBill(@ModelAttribute  Chef chef) {
        chefService.update(chef);

        return "redirect:/chef";
    }



}
