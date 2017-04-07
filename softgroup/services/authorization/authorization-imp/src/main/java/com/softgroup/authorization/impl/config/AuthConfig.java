package com.softgroup.authorization.impl.config;

import com.softgroup.common.dao.impl.configurations.DaoImplAppCfg;
import com.softgroup.common.datamapper.configuration.DataMapperAppCfg;
import com.softgroup.common.token.impl.configurations.TokenCfg;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by anton on 28.02.17.
 */
@Configuration
@ComponentScan(basePackages = {"com.softgroup.authorization"})
@Import({DataMapperAppCfg.class, DaoImplAppCfg.class,  TokenCfg.class})
public class AuthConfig {
}
