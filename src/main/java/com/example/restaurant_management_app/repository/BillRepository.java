package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Bill;
import org.springframework.stereotype.Repository;

@Repository
public class BillRepository extends InFileRepository<Bill>{
    public BillRepository() {
        super("src/main/resources/data/bills.json", Bill.class);
    }

    @Override
    public String getId(Bill entity) {
        return entity.getId();
    }
}
