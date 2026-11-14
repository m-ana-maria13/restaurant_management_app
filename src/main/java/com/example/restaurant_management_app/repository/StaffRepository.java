package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Staff;
import org.springframework.stereotype.Repository;

@Repository
public class StaffRepository extends InFileRepository<Staff>{

    public StaffRepository(OrderRepository orderRepository) {
        super("src/main/resources/data/staff.json", Staff.class);}

    @Override
    public String getId(Staff entity) {
        return entity.getId();
    }
}