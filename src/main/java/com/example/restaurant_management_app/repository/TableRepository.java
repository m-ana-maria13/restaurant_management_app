package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.model.Order;
import com.example.restaurant_management_app.model.Table;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TableRepository extends InFileRepository<Table> {

    private final OrderRepository orderRepository;

    public TableRepository(OrderRepository orderRepository) {
        super("src/main/resources/data/tables.json", Table.class);
        this.orderRepository = orderRepository;
    }

    @Override
    public String getId(Table entity) {
        return entity.getId();
    }

    private void resolveOrders(Table table) {
        List<Order> fullOrders = new ArrayList<>();

        for (Order order : table.getOrders()) {
            Order fullOrder = orderRepository.findById(order.getId());
            //extrag id ul si dupa reocnstruiesc intregul obiect
            if (fullOrder != null) {
                fullOrders.add(fullOrder);
            }
        }

        table.setOrders(fullOrders);
    }

    @Override
    public List<Table> findAll() {
        List<Table> tables = readFromFile();

        for (Table table : tables) {
            resolveOrders(table);
        }

        return tables;
    }

    @Override
    public Table findById(String id) {
        Table table = super.findById(id);

        if (table != null) {
            resolveOrders(table);
        }

        return table;
    }
}
//
//    public Table findByNumber(int number){
//        for (Table table : entities) { // daca entities era private nu aveam acces
//            if (number == table.getNumber()) {
//                return table;
//            }
//        }
//        return null;
//    }
//
//    public void assignOrder(String tableId, Order order){
//        Table table= findById(tableId);
//        table.addOrders(order);
//    }

