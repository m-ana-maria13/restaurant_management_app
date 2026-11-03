package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Staff;
import org.springframework.stereotype.Repository;

@Repository
public class StaffRepository extends InMemoryRepository<Staff>{

    @Override
    public String getId(Staff entity) {
        return entity.getId();
    }
}