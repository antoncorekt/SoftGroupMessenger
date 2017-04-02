package com.softgroup.common.dao.api.repository;

import com.softgroup.common.dao.api.entities.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by anton on 02.04.17.
 */
@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends CrudRepository<E, String> {
}