package com.softgroup.frontend.rest.controllers;

import com.softgroup.common.datamapper.JacksonDataMapper;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.router.api.IMainRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * Created by anton on 03.03.17.
 */
@RestController
@RequestMapping(path = "/test", method = RequestMethod.GET)
public class Control {


    @RequestMapping(path = "/get")
    public String testRest(){
        return "Hello word";
    }
}
