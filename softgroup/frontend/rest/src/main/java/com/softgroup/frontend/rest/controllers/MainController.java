package com.softgroup.frontend.rest.controllers;

import com.softgroup.common.datamapper.DataMapper;
import com.softgroup.common.datamapper.JacksonDataMapper;
import com.softgroup.common.protocol.*;
import com.softgroup.common.protocol.ResponseStatus;
import com.softgroup.common.router.api.IMainRouter;
import com.softgroup.common.router.impl.MainRouter;
import com.softgroup.common.token.impl.handlerimpl.ServiceToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = "/main")
    public Response<?> getMessage(@RequestHeader final String token,
                                  @RequestBody final Request<?> request) {

        try{
            return mainRouter.handle(request);
        }
        catch (Exception e){
            System.out.println("Error token or mainrouter" + e.toString());
            return new Response<>(null, null, new ResponseStatus(400,"error"));
        }


    }

    @RequestMapping(path = "/test")
    public String testRest(){
        return "Testing work!";
    }
}
