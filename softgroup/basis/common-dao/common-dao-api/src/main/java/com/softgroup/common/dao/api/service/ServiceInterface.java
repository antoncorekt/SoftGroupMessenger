package com.softgroup.common.dao.api.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Created by anton on 15.03.17.
 */
@Service
public interface ServiceInterface<T extends CrudRepository> {
}
