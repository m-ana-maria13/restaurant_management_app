package com.example.restaurant_management_app.controller;


import com.example.restaurant_management_app.model.Server;
import com.example.restaurant_management_app.service.ServerService;
import com.example.restaurant_management_app.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/server")
public class ServerController {
    private final ServerService serverService;

    @Autowired
    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }


    @GetMapping
    public String getAllServer(Model model){
        model.addAttribute("servers",serverService.getAll()) ;
        return "server/index";
    }

    @GetMapping("/new")
    public String showForm(Model model){
        model.addAttribute("server",new Server()) ;
        return "server/form";
    }

    @PostMapping
    public String saveServer(Server server){
        serverService.addNewServer(server.getName(),server.getDesignation());
        return "redirect:/server";
    }


    @PostMapping("/{id}/delete")
    public String deleteServer(@PathVariable("id") String id){
        serverService.deleteServer(id);
        return "redirect:/server";
    }
}
