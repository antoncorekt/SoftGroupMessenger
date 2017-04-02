package com.softgroup.common.dao.impl.service;

import com.softgroup.common.dao.api.entities.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by anton on 11.03.17.
 */
public abstract class BaseCRUDService<T extends BaseEntity, V extends CrudRepository<T,String>> {

    @Autowired
    private V repository;

    public T save(T entity){
        return repository.save(entity);
    }

    public Iterable<T> save(Iterable<T> entities){
        return repository.save(entities);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    public void delete(Iterable<T> entities) {
        repository.delete(entities);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Iterable<T> findAll() {
        return repository.findAll();
    }

    public void findAll(Iterable<String> ids) {
        repository.findAll(ids);
    }

    public T findOne(String id) {
        return repository.findOne(id);
    }

    public void exists(String id) {
        repository.exists(id);
    }

    public V getRepository() {
        return repository;
    }
}
