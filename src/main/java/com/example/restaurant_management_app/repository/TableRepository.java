package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.model.Chef;
import com.example.restaurant_management_app.model.Order;
import com.example.restaurant_management_app.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

interface JpaTableRepository extends JpaRepository<Table,String> {}

@Repository
public class TableRepository implements IRepository<Table> {
    private final IRepository<Table>  repository;

    public TableRepository(@Value("${spring.profiles.active:file}") String activeProfile,
                           @Autowired(required = false) JpaTableRepository jpaTableRepository) {

        if ("memory".equalsIgnoreCase(activeProfile)){

            this.repository= new InMemoryRepository<Table>(){
                @Override
                public String getId(Table table){
                    return table.getId();
                }
            };
        }
        else if("mysql".equalsIgnoreCase(activeProfile)){

            this.repository=new MySQLRepository<Table>(jpaTableRepository) {
                @Override
                public String getId(Table table){
                    return table.getId();
                }

            };
        }else{

            this.repository=new InFileRepository<Table>("src/main/resources/data/tables.json",Table.class) {
                @Override
                public String getId(Table table) {
                    return table.getId();
                }
            };
        }


    }

    @Override
    public Table save(Table table) {
        return repository.save(table);
    }

    @Override
    public List<Table> findAll() {
        return repository.findAll();
    }

    @Override
    public Table findById(String id) {
        return  repository.findById(id);
    }

    @Override
    public Boolean delete(Table table) {
        return  repository.delete(table);
    }


//
//    public void assignOrder(String tableId, Order order){
//        Table table= findById(tableId);
//        table.addOrders(order);
//        save(table);
//    }

}



