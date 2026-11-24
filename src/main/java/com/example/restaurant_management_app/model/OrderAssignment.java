package com.example.restaurant_management_app.model;

import java.util.Random;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderAssignment")
public class OrderAssignment {

    //private static int counter=1;
    @Id
    private String id;

    private String orderId;
    @ManyToOne
    @JoinColumn(name = "orderId",insertable = false, updatable = false)
    @JsonIgnore
    private Order order;

    private String staffId;
    @ManyToOne
    @JoinColumn(name = "staffId",insertable = false, updatable = false)
    private Staff staff;

    public OrderAssignment( String orderId, String staffId) {
      //  this.id = String.valueOf(counter);
       // counter++;
        this.id = String.valueOf(new Random().nextInt(100000) + 1);
        this.orderId = orderId;
        this.staffId = staffId;
    }

    public OrderAssignment(){}

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

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
