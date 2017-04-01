package com.softgroup.frontend.rest.configurations;

import com.softgroup.authorization.impl.config.AuthConfig;
import com.softgroup.common.router.configurations.RouterCfg;
import com.softgroup.common.token.impl.configurations.TokenCfg;
import com.softgroup.frontend.rest.security.SecurityInit;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

/**
 * Created by anton on 03.03.17.
 */

@Configuration
@ComponentScan(basePackages = "com.softgroup.frontend.rest")
@Import(value = {TokenCfg.class,
        SecurityConfig.class, SecurityInit.class, RouterCfg.class, AuthConfig.class})
public class RestConfig {

}
