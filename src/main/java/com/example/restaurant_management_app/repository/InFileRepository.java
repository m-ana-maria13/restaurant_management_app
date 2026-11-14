package com.example.restaurant_management_app.repository;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class InFileRepository<T> implements IRepository<T>{
    private final String filePath;
    private final Class<T> entityClass;
    private final ObjectMapper objectMapper;
    public abstract String getId(T entity);

    public InFileRepository(String filePath, Class<T> entityClass) {
        this.filePath = filePath;
        this.entityClass = entityClass;
        this.objectMapper = new ObjectMapper();
    }

    protected List<T> readFromFile(){
        File file=  new File(filePath);
        if( !file.exists() || file.length()==0){
            return new ArrayList<>();
        }

        try{
            return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class,entityClass));
            } catch (Exception e){
                 throw new RuntimeException("Could not read from file: "+filePath, e);
             }
    }


    private void writeToFile(List<T> entities){
        File file= new File(filePath);

        if(file.getParentFile() != null){// de ex daca am filepathu src/main/resources/data/entity.json, returneaza ..../data
            file.getParentFile().mkdirs(); //daca nu exista parent file, il creeaza, dca exista nu schimba cu nimic.
                                            // la noi exista 100% data, deci nu are un rol semnificativ
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file,entities);
    }



    @Override
    public T save(T entity) {

        if (entity != null) {
            String id = getId(entity);
            if (id != null) {
                delete(entity); // daca entitiy e deja, sterg datele vechi si il salvez din nou (update)
                List<T> entities = readFromFile();
                entities.add(entity);
                writeToFile(entities);

                return entity;
            }
        }
        return null;

    }


    @Override
    public List<T> findAll() {
        return readFromFile();
    }


    @Override
    public T findById(String id) {
        if (id== null){
            return null;
        }

       List<T> entities = readFromFile();
        for( T entity : entities){
            if(id.equals(getId(entity))){
                return entity;
            }
        }
        return null;
    }



    @Override
    public Boolean delete(T entity) {
        if(entity==null || getId(entity)==null){
            return false;
        }
        List<T> entities = readFromFile();
        String id= getId(entity);

        Boolean removed= entities.removeIf(item-> id.equals(getId(item)));

        if(removed){
            writeToFile(entities);
        }

        return removed;

    }
}
