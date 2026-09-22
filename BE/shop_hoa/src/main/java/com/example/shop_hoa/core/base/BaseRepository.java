package com.example.shop_hoa.core.base;

import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public class BaseRepository<T, ID> {
    private final EntityManager entityManager;
    private final Class<T> entityClass;

    public BaseRepository(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    public List<T> findAll() {
        return entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
    }

    public T findById(ID id) {
        return entityManager.find(entityClass, id);
    }

    @Transactional
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    public void deleteById(ID id) {
        T entity = findById(id);
        if (entity != null) entityManager.remove(entity);
    }
}