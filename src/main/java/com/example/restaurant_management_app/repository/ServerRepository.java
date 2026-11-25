package com.example.restaurant_management_app.repository;

import com.example.restaurant_management_app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

interface JpaServerRepository extends JpaRepository<Server,String> {}

@Repository
public class ServerRepository implements IRepository<Server> {
    private final IRepository<Server> repository;

    public ServerRepository(@Value("${spring.profiles.active:file}") String activeProfile,
                            @Autowired(required = false) JpaServerRepository jpaServerRepository) {

        if ("memory".equalsIgnoreCase(activeProfile)){

            this.repository= new InMemoryRepository<Server>(){
                @Override
                public String getId(Server server){
                    return server.getId();
                }
            };
        }
        else if("mysql".equalsIgnoreCase(activeProfile)){

            this.repository=new MySQLRepository<Server>(jpaServerRepository) {
                @Override
                public String getId(Server server){
                    return server.getId();
                }

            };
        }else{

            this.repository=new InFileRepository<Server>("src/main/resources/data/servers.json",Server.class) {
                @Override
                public String getId(Server server) {
                    return server.getId();
                }
            };
        }


    }

    @Override
    public Server save(Server server) {
        return repository.save(server);
    }

    @Override
    public List<Server> findAll() {
        return repository.findAll();
    }

    @Override
    public Server findById(String id) {
        return  repository.findById(id);
    }

    @Override
    public Boolean delete(Server server) {
        return  repository.delete(server);
    }

}