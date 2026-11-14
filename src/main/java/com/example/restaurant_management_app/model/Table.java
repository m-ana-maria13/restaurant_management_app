package com.example.restaurant_management_app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Table {

    public enum Status{
        FREE, OCCUPIED
    }
   // private static int counter=1;
    private String id;
    private List<Order> orders;
    private Status occupiedStatus;
    private int numberPeople;

    public Table( List<Order> orders, int nrPeople) {
     //   this.id = String.valueOf(counter);
     //   counter++;
        this.id= UUID.randomUUID().toString();
        this.orders = orders;
        this.occupiedStatus = Status.FREE;
        this.numberPeople = nrPeople;
    }


    public Table(){
        this.orders=new ArrayList<>();
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



    public int getNumberPeople() {
        return numberPeople;
    }



    public void setNumberPeople(int numberPeople) {
        this.numberPeople = numberPeople;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Order> getOrders() {
        return orders;
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
