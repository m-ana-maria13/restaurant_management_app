package com.example.restaurant_management_app.model;

import java.util.UUID;

public abstract class Staff {
    public enum Status{
        FREE, BUSY
    }
    //private static int counter=1;
    private String name;
    private String id;
    private Status status; // neue Eigenschaft

    public Staff( String name) {
       // this.id = String.valueOf(counter);
      //  counter++;
        this.id= UUID.randomUUID().toString();
        this.name = name;
        this.status=Status.FREE;
    }

    public Staff(){}

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
