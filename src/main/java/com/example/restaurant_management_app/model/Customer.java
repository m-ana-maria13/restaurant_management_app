package com.example.restaurant_management_app.model;

import java.util.List;

public class Customer {

    private static int counter=1;
    private String id;
    private String name;
    private List<Order> orders;
    private double discount;


    public Customer(String name, List<Order> orders) {
        this.id = String.valueOf(counter);
        counter++;
        this.name = name;
        this.orders = orders;
        this.discount=0.0;

    }

    public Customer(){}


    public Customer(String name, List<Order> orders, double discount) {
        this.id = String.valueOf(counter);
        counter++;
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

    public void setOrders(Order order) {
        if(order==null){
            throw new IllegalArgumentException("Cannot find order!");
        }
        this.orders.add(order);
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
