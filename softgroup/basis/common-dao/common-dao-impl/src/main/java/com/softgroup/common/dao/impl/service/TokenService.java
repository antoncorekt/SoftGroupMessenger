package com.softgroup.common.dao.impl.service;

import com.softgroup.common.dao.api.entities.TokenEntity;
import com.softgroup.common.dao.impl.repositories.TokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by anton on 15.03.17.
 */
@Component
public class TokenService extends BaseCRUDService<TokenEntity, TokenRepository, String> {

}
