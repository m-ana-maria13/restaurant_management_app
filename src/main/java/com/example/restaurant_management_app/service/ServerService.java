package com.example.restaurant_management_app.service;

import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.model.Chef;
import com.example.restaurant_management_app.model.Server;
import com.example.restaurant_management_app.model.Staff;
import com.example.restaurant_management_app.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerService {
    private final ServerRepository serverRepository;

    @Autowired
     public ServerService(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }


    public Server addNewServer(String name, String designation ){
        Server server= new Server(name, designation);
        return serverRepository.save(server);

    }


    public List<Server> getAll() {
        return serverRepository.findAll();
    }


    public Server getById(String id) {
        return serverRepository.findById(id);
    }


    public Boolean removeStaff(String id) {
        Server server = existingServer(id);
        return serverRepository.delete(server);
    }

    public Server changeDesignation(String id, String newDesignation) {
        Server server = serverRepository.findById(id);
        server.setDesignation(newDesignation);
        return serverRepository.save(server);

    }



    public Boolean isBusy(String id) {
        Server server = existingServer(id);
        return (server.getStatus()).equals(Server.Status.BUSY);
    }

    public  Server changeStatus(String id, Server.Status status){
        Server server = existingServer(id);

        if (status == null
                || !(status.equals(Server.Status.BUSY) || status.equals(Server.Status.FREE))) {
            throw new IllegalArgumentException("Status must be Occupied or Free");
        }

        server.setStatus(status);
        return serverRepository.save(server);
    }


    private Server existingServer(String id) {
        Server server = serverRepository.findById(id);
        if (server == null) {
            throw new IllegalArgumentException("Id not valid!");
        }
        return server;
    }

    public Server deleteServer(String serverId){
        if(serverId == null)
            throw new IllegalArgumentException("Id null!");

        Server server = serverRepository.findById(serverId);
        if(server== null)
            throw new IllegalArgumentException("Invalid id!");

        serverRepository.delete(server);
        return server;
    }

    public Server update(Server server){
        serverRepository.save(server);
        return server;
    }

}


