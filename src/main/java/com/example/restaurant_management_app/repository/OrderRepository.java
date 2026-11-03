package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends InMemoryRepository<Order>{

    @Override
    public String getId(Order entity) {
        return entity.getId();
    }
}
