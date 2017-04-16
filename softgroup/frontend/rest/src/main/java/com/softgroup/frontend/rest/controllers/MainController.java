package com.softgroup.frontend.rest.controllers;


import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.router.api.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public Response<?> getMessage(@RequestBody final Request<?> request) {

        try{
            return mainRouter.handle(request);
        }
        catch (Exception e){

            return ResponseFactory.createResponse(request, Status.INTERNAL_SERVER_ERROR);
        }
    }

}
