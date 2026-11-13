package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends InFileRepository<Order>{

    public OrderRepository() {
        super("src/main/resources/data/orders.json", Order.class);
    }

    @Override
    public String getId(Order entity) {
        return entity.getId();
    }
}
