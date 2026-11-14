package com.example.restaurant_management_app.repository;
import com.example.restaurant_management_app.model.Customer;
import com.example.restaurant_management_app.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository extends InFileRepository<Customer>{

    public CustomerRepository() {
        super("src/main/resources/data/customers.json", Customer.class);

    }

    @Override
    public String getId(Customer entity) {
        return entity.getId();
    }



    public void assignOrder(String customerId, Order order){
        Customer customer= findById(customerId);
        customer.addOrders(order);
        save(customer);
    }
}


