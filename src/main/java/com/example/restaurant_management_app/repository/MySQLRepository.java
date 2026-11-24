package com.example.restaurant_management_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class MySQLRepository<T> implements IRepository<T> {
    private final JpaRepository<T, String> jpaRepository;

    public MySQLRepository(JpaRepository<T, String> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public abstract String getId(T entity);

    @Override
    public T save(T entity) {

        // INSERT dacă id-ul nu există în DB,
        // UPDATE dacă id-ul există deja
        // returnează entitatea salvată
        return jpaRepository.save(entity);
    }

    @Override
    public List<T> findAll() {

        return jpaRepository.findAll();
    }

    @Override
    public T findById(String id) {
        if (id == null) {
            return null;
        }
        return jpaRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean delete(T entity) {
        if (entity == null) {
            return false;
        }
        String id = getId(entity);
        if (id != null && jpaRepository.existsById(id)) {
            jpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
