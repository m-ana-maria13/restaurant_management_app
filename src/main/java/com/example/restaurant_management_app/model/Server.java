package com.example.restaurant_management_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "servers")
public class Server extends Staff {
    private String designation;

    public Server( String name, String designation) {
        super( name);
        this.designation=designation;
    }

    public Server(){}

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
