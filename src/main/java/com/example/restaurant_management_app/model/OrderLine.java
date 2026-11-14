package com.example.restaurant_management_app.model;

import java.util.UUID;

public class OrderLine {

  //  private static int counter=1;
    private String id;
    private String menuItemId;
    private double quantity;

    public OrderLine(String menuItemId, double quantity) {
        //this.id = String.valueOf(counter);
       // counter++;
        this.id= UUID.randomUUID().toString();
        this.menuItemId = menuItemId;
        this.quantity = quantity;
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
}
