package com.example.restaurant_management_app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;


@Entity
@jakarta.persistence.Table(name = "tables")
public class Table {


    public enum Status{
        FREE, OCCUPIED
    }
   // private static int counter=1;
    @Id
    private String id;
    private List<Order> orders;

    @Enumerated(EnumType.STRING)
    private Status occupiedStatus;

    private int numberPeople;

    public Table( List<Order> orders, int numberPeople) {
     //   this.id = String.valueOf(counter);
     //   counter++;
        this.id = String.valueOf(new Random().nextInt(100000) + 1);
        this.orders = orders;
        this.occupiedStatus = Status.FREE;
        this.numberPeople = numberPeople;
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
