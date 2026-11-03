package com.example.restaurant_management_app.service;


import com.example.restaurant_management_app.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restaurant_management_app.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    public Customer registerCustomer(Customer customer){
        if(customer==null || customer.getId()==null){
            throw new IllegalArgumentException("Client invalid");
        }
        if(customer.getDiscount()<0 || customer.getDiscount()>70){
            throw new IllegalArgumentException("Discount between 0%-70%");
        }

        return customerRepository.save(customer);
    }


    public Customer updateDiscount(String customerId, int discount){
        Customer customer= customerRepository.findById(customerId);
        if(customer==null ){
            throw new IllegalArgumentException("Client not found!");
        }
        if(discount<0 || discount>70){
            throw new IllegalArgumentException("Discount between 0%-70%");
        }
        customer.setDiscount(discount);
        return customerRepository.save(customer);
    }



    public Customer getCustomerById(String customerId){
        return customerRepository.findById(customerId);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }


}
