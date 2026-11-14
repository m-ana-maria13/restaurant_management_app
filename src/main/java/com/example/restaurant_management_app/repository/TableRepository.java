package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Order;
import com.example.restaurant_management_app.model.Table;
import org.springframework.stereotype.Repository;


@Repository
public class TableRepository extends InFileRepository<Table> {

    public TableRepository() {
        super("src/main/resources/data/tables.json", Table.class);

    }

    @Override
    public String getId(Table entity) {
        return entity.getId();
    }


//
//    public void assignOrder(String tableId, Order order){
//        Table table= findById(tableId);
//        table.addOrders(order);
//        save(table);
//    }

}



