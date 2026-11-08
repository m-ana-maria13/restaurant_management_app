package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Server;
import com.example.restaurant_management_app.model.Staff;
import org.springframework.stereotype.Repository;

@Repository
public class ServerRepository extends InMemoryRepository<Server> {

    @Override
    public String getId(Server entity) {
        return entity.getId();
    }


}