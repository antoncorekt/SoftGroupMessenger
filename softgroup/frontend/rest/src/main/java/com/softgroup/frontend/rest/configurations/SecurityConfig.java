package com.softgroup.frontend.rest.configurations;

import com.softgroup.frontend.rest.security.MyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by anton on 18.03.17.
 */

@Configuration
@EnableWebSecurity
@Import(value = {MyFilter.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String PUBLIC_CONTROLLER_PATH = "/root/public/**";

    @Autowired
    private MyFilter myFilter;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(PUBLIC_CONTROLLER_PATH);
        web.debug(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(myFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable().authorizeRequests().anyRequest().authenticated();
    }

}
