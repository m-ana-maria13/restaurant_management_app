package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.model.Chef;
import org.springframework.stereotype.Repository;

@Repository
public class ChefRepository extends InFileRepository<Chef> {

    public ChefRepository() {
        super("src/main/resources/data/chefs.json", Chef.class);
    }

    @Override
    public String getId(Chef entity) {
        return entity.getId();
    }


}

