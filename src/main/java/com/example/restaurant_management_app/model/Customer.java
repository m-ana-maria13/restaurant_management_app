package com.example.restaurant_management_app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

   // private static int counter=1;
    @Id
    private String id;
    private String name;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Order> orders;
    private double discount;


    public Customer(String name, List<Order> orders) {
        this.id = String.valueOf(new Random().nextInt(100000) + 1);
      //  counter++;
        this.name = name;
        this.orders = orders;
        this.discount=0.0;

    }

    public Customer(){
        this.orders= new ArrayList<>();
    }


    public Customer(String name, List<Order> orders, double discount) {
       // this.id = String.valueOf(counter);
       // counter++;
        this.id = String.valueOf(new Random().nextInt(100000) + 1);
        this.name = name;
        this.orders = orders;
        this.discount=discount; //procente, pana in 70 la suta

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }


    public void addOrders(Order order) {
        if(order==null){
            throw new IllegalArgumentException("Cannot find order!");
        }
        if(this.orders==null){
            this.orders=new ArrayList<>();
        }
        this.orders.add(order);
    }


    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
