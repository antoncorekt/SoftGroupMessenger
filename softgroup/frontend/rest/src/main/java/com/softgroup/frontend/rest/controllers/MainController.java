package com.softgroup.frontend.rest.controllers;

import com.softgroup.common.datamapper.DataMapper;
import com.softgroup.common.datamapper.JacksonDataMapper;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.router.api.IMainRouter;
import com.softgroup.common.router.impl.MainRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * Created by anton on 03.03.17.
 */
@RestController
@RequestMapping(path = "/root",
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {

    @Autowired
    private MainRouter mainRouter;

    @Autowired
    private DataMapper jacksonDataMapper;

    @RequestMapping(path = "/main")
    public Response<?> getMessage(@RequestBody Request<?> request){

        // todo if request==null ?

        return mainRouter.handle(request);
    }

    @RequestMapping(path = "/test")
    public String testRest(){
        return "Testing work!";
    }
}
