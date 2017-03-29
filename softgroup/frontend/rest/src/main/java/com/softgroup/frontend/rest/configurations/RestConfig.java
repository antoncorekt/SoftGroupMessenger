package com.softgroup.frontend.rest.configurations;

import com.softgroup.common.token.impl.configurations.TokenCfg;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

/**
 * Created by anton on 03.03.17.
 */

@Configuration
@ComponentScan(basePackages = "com.softgroup.frontend.rest", excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class))
@Import(value = {TokenCfg.class})
public class RestConfig {
}
