package com.softgroup.frontend.rest.configurations;

import com.softgroup.authorization.impl.config.AuthConfig;
import com.softgroup.common.datamapper.DataMapper;
import com.softgroup.common.datamapper.configuration.DataMapperAppCfg;
import com.softgroup.common.router.configurations.RouterConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by anton on 03.03.17.
 */
@Configuration
@ComponentScan("com.softgroup.frontend.rest")
@Import({DataMapperAppCfg.class, RouterConfig.class, AuthConfig.class})
public class RestConfig {
}
