package com.example.restaurant_management_app.model;

import java.util.List;

public class Table {

    public enum Status{
        FREE, OCCUPIED
    }
    private static int counter=1;
    private String id;
    private int number;
    private List<Order> orders;
    private Status occupiedStatus;

    public Table(int number, List<Order> orders) {
        this.id = String.valueOf(counter);
        counter++;
        this.number = number;
        this.orders = orders;
        this.occupiedStatus = Status.FREE;
    }

    public Table(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrders(Order order) {
        if(order==null){
            throw new IllegalArgumentException("Cannot find order!");
        }
        this.orders.add(order);
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Status getOccupiedStatus() {
        return occupiedStatus;
    }

    public void setOccupiedStatus(Status occupiedStatus) {
        this.occupiedStatus = occupiedStatus;
    }
}
