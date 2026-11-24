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
        return null;
    }

    @Override
    public List<T> findAll() {
        return List.of();
    }

    @Override
    public T findById(String id) {
        return null;
    }

    @Override
    public Boolean delete(T entity) {
        return null;
    }
}
