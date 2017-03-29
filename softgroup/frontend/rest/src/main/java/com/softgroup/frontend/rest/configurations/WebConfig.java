package com.softgroup.frontend.rest.configurations;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by anton on 03.03.17.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.softgroup.frontend.rest")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper mapper = new ObjectMapper().
                disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).
                disable(SerializationFeature.FAIL_ON_EMPTY_BEANS).
                setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
                setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE).
                setSerializationInclusion(JsonInclude.Include.NON_NULL);

        converters.add(new MappingJackson2HttpMessageConverter(mapper));
    }
}
