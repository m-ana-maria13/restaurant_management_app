package com.example.restaurant_management_app.service;

import com.example.restaurant_management_app.model.*;
import com.example.restaurant_management_app.repository.ChefRepository;
import com.example.restaurant_management_app.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffService {
    private final ChefRepository chefRepository;
    private final ServerRepository serverRepository;

    @Autowired
    public StaffService(ChefRepository chefRepository, ServerRepository serverRepository) {
        this.chefRepository = chefRepository;
        this.serverRepository = serverRepository;
    }

    public List<Staff> getAllStaff() {
        List<Staff> allStaff = new ArrayList<>();
        allStaff.addAll(chefRepository.findAll());
        allStaff.addAll(serverRepository.findAll());
        return allStaff;
    }

    public Staff findById(String id) {
        Staff chef = chefRepository.findById(id);
        if (chef != null) {
            return chef;
        }
        return serverRepository.findById(id);
    }

    public Staff save(Staff staff) {
        if (staff instanceof Chef) {
            return chefRepository.save((Chef) staff);
        } else if (staff instanceof Server) {
            return serverRepository.save((Server) staff);
        }
        return null;
    }

    public boolean delete(Staff staff) {
        if (staff instanceof Chef) {
            return chefRepository.delete((Chef) staff);
        } else if (staff instanceof Server) {
            return serverRepository.delete((Server) staff);
        }
        return false;
    }



    public Boolean isBusy(String id) {
        Staff staff = existingStaff(id);
        return (staff.getStatus()).equals(Staff.Status.BUSY);
    }

    public  Staff changeStatus(String id, Staff.Status status){
        Staff staff = existingStaff(id);

        if (status == null
                || !(status.equals(Staff.Status.BUSY) || status.equals(Staff.Status.FREE))) {
            throw new IllegalArgumentException("Status must be Occupied or Free");
        }

        staff.setStatus(status);

        if (staff instanceof Chef)
            return chefRepository.save((Chef) staff);
        else
            return serverRepository.save((Server) staff);
    }


    private Staff existingStaff(String id) {
        Staff staff = chefRepository.findById(id);

        if(staff ==null){
            staff = serverRepository.findById(id);
        }
        if (staff == null) {
            throw new IllegalArgumentException("Id not valid!");
        }
        return staff;
    }


    public Staff update(Staff staff){
        if (staff instanceof Server)
            serverRepository.save((Server) staff);

        else if (staff instanceof Chef)
            chefRepository.save((Chef) staff);
        return staff;
    }

}


