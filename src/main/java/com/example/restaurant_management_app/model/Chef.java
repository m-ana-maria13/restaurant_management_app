package com.example.restaurant_management_app.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "chefs")
public class Chef extends Staff {
    private String specialization;

    public Chef( String name, String specialization) {
        super(name);
        this.specialization = specialization;
    }

    public Chef() {}

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
