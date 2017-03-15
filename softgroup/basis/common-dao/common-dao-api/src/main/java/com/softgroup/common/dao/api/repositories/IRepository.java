package com.softgroup.common.dao.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by anton on 15.03.17.
 */
@Repository
public interface IRepository<T, V extends Serializable> extends CrudRepository<T, V> {
}
