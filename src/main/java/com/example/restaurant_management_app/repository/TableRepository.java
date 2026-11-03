package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Table;
import org.springframework.stereotype.Repository;

@Repository
public class TableRepository extends InMemoryRepository<Table> {

    @Override
    public String getId(Table entity) {
        return entity.getId();
    }

    public Table findByNumber(int number){
        for (Table table : entities) { // daca entities era private nu aveam acces
            if (number == table.getNumber()) {
                return table;
            }
        }
        return null;
    }
}
