package com.example.restaurant_management_app.service;

import com.example.restaurant_management_app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restaurant_management_app.repository.StaffRepository;

import java.util.List;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepo) {
        this.staffRepository = staffRepo;
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


    public Staff update(Staff staff){
        staffRepository.save(staff);
        return staff;
    }

}


