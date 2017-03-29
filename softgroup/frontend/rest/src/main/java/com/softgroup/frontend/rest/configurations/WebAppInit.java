package com.softgroup.frontend.rest.configurations;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by anton on 03.03.17.
 */
public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {




    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RestConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


}
