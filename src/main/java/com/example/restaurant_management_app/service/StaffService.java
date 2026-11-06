package com.example.restaurant_management_app.service;

import com.example.restaurant_management_app.model.Chef;
import com.example.restaurant_management_app.model.Order;
import com.example.restaurant_management_app.model.Server;
import com.example.restaurant_management_app.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restaurant_management_app.repository.StaffRepository;

import java.util.List;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    StaffService(StaffRepository staffRepo) {
        this.staffRepository = staffRepo;
    }

    public Chef addNewChef(String name, String specialization){
        Chef chef= new Chef(name,specialization);
         return (Chef) staffRepository.save(chef);

    }

    public Server addNewServer(String name, String designation ){
        Server server= new Server(name, designation);
        return (Server) staffRepository.save(server);

    }



    public List<Staff> getAll() {
        return staffRepository.findAll();
    }


    public Staff getById(String id) {
        return staffRepository.findById(id);
    }


    public Boolean removeStaff(String id) {
        Staff staff = existingStaff(id);
        return staffRepository.delete(staff);
    }

    public Server changeDesignation(String id, String newDesignation) {
        Staff staff = staffRepository.findById(id);

        if (!(staff instanceof Server server)) {
            throw new IllegalArgumentException("Staff " + id + " is not a server");
        }


        server.setDesignation(newDesignation);
        staffRepository.save(server);
        return server;
    }


    public Chef changeSpecialization(String id, String newSpecialization) {
        Staff staff = staffRepository.findById(id);

        if (!(staff instanceof Chef chef)) {
            throw new IllegalArgumentException("Staff " + id + " is not a chef");
        }

        chef.setSpecialization(newSpecialization);
        staffRepository.save(chef);
        return chef;
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
        return staffRepository.save(staff);
    }


    private Staff existingStaff(String id) {
        Staff staff = staffRepository.findById(id);
        if (staff == null) {
            throw new IllegalArgumentException("Id not valid!");
        }
        return staff;
    }

    public Staff deleteStaff(String staffId){
        if(staffId == null)
            throw new IllegalArgumentException("Id null!");

        Staff staff = staffRepository.findById(staffId);
        if(staff== null)
            throw new IllegalArgumentException("Invalid id!");

        staffRepository.delete(staff);
        return staff;
    }

}


