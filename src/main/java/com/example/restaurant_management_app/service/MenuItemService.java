package com.example.restaurant_management_app.service;

import com.example.restaurant_management_app.model.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restaurant_management_app.repository.MenuRepository;

import java.util.List;

@Service
public class MenuItemService {
    private final MenuRepository menuItemRepository;

    @Autowired
    MenuItemService(MenuRepository menuItemRepo){
        this.menuItemRepository = menuItemRepo;
    }

    public List<MenuItem> getAll(){
        return menuItemRepository.findAll();
    }

    public MenuItem getById(String id) {
        return menuItemRepository.findById(id);
    }

    public MenuItem changePrice(String id, double newPrice) {

        validatePrice(newPrice);
        MenuItem item = menuItemRepository.findById(id);
        if (item == null) {
            throw new IllegalArgumentException("Item not found: " + id);
        }

        item.setPrice(newPrice);

        return menuItemRepository.save(item);
    }


    public MenuItem addNewMenuItem( String name, double price) {

        validateName(name);
        validatePrice(price);

        MenuItem item = new MenuItem(name, price);

        return menuItemRepository.save(item);
    }

    private void validateName(String name) {
        if ( name == null ) {
            throw new IllegalArgumentException("The item needs a name!");
        }
    }

    private void validatePrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Invalid price!");
        }
    }
}
