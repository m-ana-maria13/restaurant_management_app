package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.model.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

interface JpaMenuRepository extends JpaRepository<MenuItem,String> {}

@Repository
public class MenuRepository implements IRepository<MenuItem>{
    private final IRepository<MenuItem> repository;
    public MenuRepository(@Value("${spring.profiles.active:file}") String activeProfile, @Autowired(required = false) JpaMenuRepository jpaMenuRepository) {
       if("memory".equalsIgnoreCase(activeProfile)){
           this.repository= new InMemoryRepository<MenuItem>() {
               @Override
               public String getId(MenuItem entity) {
                   return entity.getId();
               }
           };
       }else if("mysql".equalsIgnoreCase(activeProfile)){
           this.repository= new MySQLRepository<MenuItem>(jpaMenuRepository) {
               @Override
               public String getId(MenuItem entity) {
                   return entity.getId();
               }
           };
       }else{
           this.repository= new InFileRepository<MenuItem>("src/main/resources/data/menu-items.json", MenuItem.class) {
               @Override
               public String getId(MenuItem entity) {
                   return entity.getId();
               }
           };
       }
    }


    @Override
    public MenuItem save(MenuItem entity) {
        return repository.save(entity);
    }

    @Override
    public List<MenuItem> findAll() {
        return repository.findAll();
    }

    @Override
    public MenuItem findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Boolean delete(MenuItem entity) {
        return repository.delete(entity);
    }
}
