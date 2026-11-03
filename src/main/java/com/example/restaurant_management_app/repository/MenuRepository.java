package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.MenuItem;
import org.springframework.stereotype.Repository;

@Repository
public class MenuRepository extends InMemoryRepository<MenuItem>{
    @Override
    public String getId(MenuItem entity) {
        return entity.getId();
    }
}
