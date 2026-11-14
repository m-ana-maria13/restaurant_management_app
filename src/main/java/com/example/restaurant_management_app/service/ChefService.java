package com.example.restaurant_management_app.service;

import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.model.Chef;
import com.example.restaurant_management_app.repository.ChefRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefService {
    private final ChefRepository chefRepository;


    public  ChefService(ChefRepository chefRepository) {
        this.chefRepository = chefRepository;
    }


    public Chef saveChef(String name, String specialization){
        Chef chef= new Chef(name,specialization);
        return chefRepository.save(chef);

    }

    public List<Chef> getAll() {
        return chefRepository.findAll();
    }


    public Chef getById(String id) {
        return chefRepository.findById(id);
    }


    public Boolean removeStaff(String id) {
        Chef chef = existingChef(id);
        return chefRepository.delete(chef);
    }



    public Chef changeSpecialization(String id, String newSpecialization) {
        Chef chef = chefRepository.findById(id);
        chef.setSpecialization(newSpecialization);
        return chefRepository.save(chef);

    }


    public Boolean isBusy(String id) {
        Chef chef = existingChef(id);
        return (chef.getStatus()).equals(Chef.Status.BUSY);
    }

    public  Chef changeStatus(String id, Chef.Status status){
        Chef chef = existingChef(id);

        if (status == null
                || !(status.equals(Chef.Status.BUSY) || status.equals(Chef.Status.FREE))) {
            throw new IllegalArgumentException("Status must be Occupied or Free");
        }

        chef.setStatus(status);
        return chefRepository.save(chef);
    }


    private Chef existingChef(String id) {
        Chef chef = chefRepository.findById(id);
        if (chef == null) {
            throw new IllegalArgumentException("Id not valid!");
        }
        return chef;
    }

    public Chef deleteChef(String chefId){
        if(chefId == null)
            throw new IllegalArgumentException("Id null!");

        Chef chef = chefRepository.findById(chefId);
        if(chef== null)
            throw new IllegalArgumentException("Invalid id!");

        chefRepository.delete(chef);
        return chef;
    }

    public Chef update(Chef chef){
        chefRepository.save(chef);
        return chef;
    }

}

