package com.softgroup.common.dao.impl.service;

import com.softgroup.common.dao.api.entities.IEntity;
import com.softgroup.common.dao.api.repositories.IRepository;
import com.softgroup.common.dao.api.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by anton on 11.03.17.
 */
public abstract class BaseCRUDService<T extends IEntity, V extends IRepository<T,K>, K extends Serializable> implements IService{

    @Autowired
    protected V repository;

    public T save(T entity){
        return repository.save(entity);
    }

    public Iterable<T> save(Iterable<T> entities){
        return repository.save(entities);
    }



}
