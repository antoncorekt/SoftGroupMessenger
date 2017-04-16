package com.softgroup.frontend.rest.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by anton on 03.03.17.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.softgroup.frontend")
public class WebConfig extends WebMvcConfigurerAdapter {
}
