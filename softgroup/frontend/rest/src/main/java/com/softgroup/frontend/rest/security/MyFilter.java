package com.softgroup.frontend.rest.security;


import com.softgroup.common.protocol.RoutedData;
import com.softgroup.common.token.impl.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by anton on 19.03.17.
 */

@Component
public class MyFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private TokenService serviceToken;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        try{
            HttpServletRequest httpRequest = (HttpServletRequest)req;

            System.out.println(httpRequest.getHeader("token"));

            RoutedData routedData = serviceToken.getRoutedData(httpRequest.getHeader("token"));
           // RoutedData routedData = new RoutedData("did","uid");

            System.out.println(routedData.getDeviceID());
            System.out.println(routedData.getUserID());

            Authentication authentication = new UsernamePasswordAuthenticationToken(null, routedData);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(req,res);

        }catch (Exception tokenExceptions){
            System.out.println("err " + tokenExceptions.toString());
        }
    }

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
