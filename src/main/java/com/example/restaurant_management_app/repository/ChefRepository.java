package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Chef;
import org.springframework.stereotype.Repository;

@Repository
public class ChefRepository extends InMemoryRepository<Chef> {

    @Override
    public String getId(Chef entity) {
        return entity.getId();
    }


}

