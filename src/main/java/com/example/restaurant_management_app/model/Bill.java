package com.example.restaurant_management_app.model;

import java.util.UUID;

public class Bill {
    //private static int counter=1;
    private String id;
    private String orderId;
    private double totalAmount;

    public Bill( String orderId, double totalAmount) {
      //  this.id = String.valueOf(counter);
       // counter++;
        this.id= UUID.randomUUID().toString();
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
