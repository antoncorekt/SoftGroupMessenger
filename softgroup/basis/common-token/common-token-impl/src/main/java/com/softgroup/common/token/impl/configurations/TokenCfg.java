package com.softgroup.common.token.impl.configurations;

import com.softgroup.common.dao.impl.configurations.DaoImplAppCfg;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by anton on 15.03.17.
 */
@Configuration
@ComponentScan("com.softgroup.common.token.impl")
@Import({DaoImplAppCfg.class})
public class TokenCfg {
}
