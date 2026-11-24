package com.example.restaurant_management_app.model;

import java.util.Random;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "bills")
public class Bill {
    //private static int counter=1;
    @Id
    private String id;

    private String orderId;
    @OneToOne
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    @JsonIgnore
    private Order order;

    private double totalAmount;

    public Bill( String orderId, double totalAmount) {
      //  this.id = String.valueOf(counter);
       // counter++;
        this.id = String.valueOf(new Random().nextInt(100000) + 1);
        this.orderId = orderId;
        this.totalAmount = totalAmount;
    }

    public Bill(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
