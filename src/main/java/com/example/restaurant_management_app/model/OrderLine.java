package com.example.restaurant_management_app.model;

import java.util.Random;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderLines")
public class OrderLine {

  //  private static int counter=1;
    @Id
    private String id;

    private String menuItemId;
    @ManyToOne
    @JoinColumn(name = "menuItemId", insertable = false, updatable = false)
    private MenuItem menuItem;

    private double quantity;

    private String orderId;
    @ManyToOne
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    @JsonIgnore
    private Order order;


    public OrderLine(String menuItemId, double quantity, String orderId) {
        //this.id = String.valueOf(counter);
       // counter++;
        this.id = String.valueOf(new Random().nextInt(100000) + 1);
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public OrderLine(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
