package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.model.Customer;
import com.example.restaurant_management_app.model.Order;
import com.example.restaurant_management_app.model.Table;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository extends InFileRepository<Customer>{

    private final OrderRepository orderRepository;
    public CustomerRepository(OrderRepository orderRepository) {
        super("src/main/resources/data/cusomers.json", Customer.class);
        this.orderRepository = orderRepository;
    }

    @Override
    public String getId(Customer entity) {
        return entity.getId();
    }

    private void resolveOrders(Customer customer) {
        List<Order> fullOrders = new ArrayList<>();

        for (Order order : customer.getOrders()) {
            Order fullOrder = orderRepository.findById(order.getId());
            //extrag id ul si dupa reocnstruiesc intregul obiect
            if (fullOrder != null) {
                fullOrders.add(fullOrder);
            }
        }

        customer.setOrders(fullOrders);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = readFromFile();

        for (Customer customer :customers) {
            resolveOrders(customer);
        }

        return customers;
    }

    @Override
    public Customer findById(String id) {
        Customer customer = super.findById(id);

        if (customer != null) {
            resolveOrders(customer);
        }

        return customer;
    }
}

//    @Override
//    public void assignOrder(String customerId, Order order){
//        Customer customer= findById(customerId);
//        customer.addOrders(order);
//    }

