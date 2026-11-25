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

interface JpaOrderRepository extends JpaRepository<Order,String> {}

@Repository
public class OrderRepository implements IRepository<Order>{
    private final IRepository<Order> repository;

    public OrderRepository(@Value("${spring.profiles.active:file}") String activeProfile,
                           @Autowired(required = false) JpaOrderRepository jpaOrderRepository) {

        if ("memory".equalsIgnoreCase(activeProfile)){

            this.repository= new InMemoryRepository<Order>(){
                @Override
                public String getId(Order order){
                    return order.getId();
                }
            };
        }
        else if("mysql".equalsIgnoreCase(activeProfile)){

            this.repository=new MySQLRepository<Order>(jpaOrderRepository) {
                @Override
                public String getId(Order order){
                    return order.getId();
                }

            };
        }else{

            this.repository=new InFileRepository<Order>("src/main/resources/data/orders.json",Order.class) {
                @Override
                public String getId(Order order) {
                    return order.getId();
                }
            };
        }


    }

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Order findById(String id) {
        return  repository.findById(id);
    }

    @Override
    public Boolean delete(Order order) {
        return  repository.delete(order);
    }




}
