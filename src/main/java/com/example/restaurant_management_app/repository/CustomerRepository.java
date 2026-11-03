package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository extends InMemoryRepository<Customer>{
    @Override
    public String getId(Customer entity) {
         return entity.getId();
    }
}
