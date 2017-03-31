package com.softgroup.frontend.rest.configurations;

import com.softgroup.frontend.rest.security.MyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by anton on 19.03.17.
 */
@Configuration
public class SecurityInit extends AbstractSecurityWebApplicationInitializer {

    /*@Bean
    public MyFilter myFilter(){
        return new MyFilter();
    }*/

}
