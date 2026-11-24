package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.Bill;
import com.example.restaurant_management_app.model.Chef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

interface JpaChefRepository extends JpaRepository<Chef,String>{}


@Repository
public class ChefRepository implements IRepository<Chef> {
   private final IRepository<Chef> repository;

    public ChefRepository(@Value("${spring.profiles.active:file}") String activeProfile, @Autowired(required = false) JpaChefRepository jpaChefRepository ) {
        if("memory".equalsIgnoreCase(activeProfile)){
            this.repository= new InMemoryRepository<Chef>(){
                @Override
                public String getId( Chef chef){
                    return chef.getId();
                }
            };
        }else if("mysql".equalsIgnoreCase(activeProfile)){
            this.repository= new MySQLRepository<Chef>(jpaChefRepository) {
                @Override
                public String getId(Chef chef){
                    return chef.getId();
                }
            };
        }else{
            this.repository= new InFileRepository<Chef>("src/main/resources/data/chefs.json", Chef.class){
                @Override
                public String getId(Chef chef){
                    return  chef.getId();
                }
            };
        }
    }


    @Override
    public Chef save(Chef chef) {
        return repository.save(chef);
    }

    @Override
    public List<Chef> findAll() {
        return repository.findAll();
    }

    @Override
    public Chef findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Boolean delete(Chef chef) {
        return repository.delete(chef);
    }
}

