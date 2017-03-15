package com.softgroup.common.dao.api.service;

import com.softgroup.common.dao.api.repositories.IRepository;
import org.springframework.stereotype.Service;

/**
 * Created by anton on 15.03.17.
 */
@Service
public interface IService <T extends IRepository> {
}
