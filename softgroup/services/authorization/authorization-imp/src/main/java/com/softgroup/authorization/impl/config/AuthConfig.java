package com.softgroup.authorization.impl.config;

import com.softgroup.common.datamapper.configuration.DataMapperAppCfg;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by anton on 28.02.17.
 */
@Configuration
@ComponentScan(basePackages = {"com.softgroup.authorization"})
@Import({DataMapperAppCfg.class})
public class AuthConfig {
}
