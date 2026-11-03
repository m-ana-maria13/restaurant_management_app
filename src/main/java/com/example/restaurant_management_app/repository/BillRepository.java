package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Bill;
import org.springframework.stereotype.Repository;

@Repository
public class BillRepository extends InMemoryRepository<Bill>{
    @Override
    public String getId(Bill entity) {
        return entity.getId();
    }
}
