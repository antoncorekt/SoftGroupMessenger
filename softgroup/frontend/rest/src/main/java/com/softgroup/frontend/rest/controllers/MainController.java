package com.softgroup.frontend.rest.controllers;

import com.softgroup.common.protocol.*;
import com.softgroup.common.protocol.utils.Status;
import com.softgroup.common.protocol.utils.ResponseFactory;
import com.softgroup.common.router.api.IMainRouter;
import com.softgroup.common.router.impl.MainRouter;
import com.softgroup.common.token.impl.handlerimpl.ServiceToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
