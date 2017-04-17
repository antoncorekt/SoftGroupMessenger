package com.softgroup.frontend.rest.controllers;

import com.softgroup.common.protocol.*;
import com.softgroup.common.protocol.ResponseStatus;
import com.softgroup.common.protocol.utils.Status;
import com.softgroup.common.protocol.utils.ResponseFactory;
import com.softgroup.common.router.api.CommonRouterHandler;
import com.softgroup.common.router.impl.MainRouter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.softgroup.common.router.api.Handler;
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
public class PublicController {

    @Autowired
    private Handler mainRouter;

    @RequestMapping(path = "/public")
    public Response<?> getPublicMessage(@RequestBody Request<?> request){
        try{
             if (!"authorization".equals(request.getHeader().getType())){
                 return ResponseFactory.createResponse(Status.BAD_REQUEST);
             }

            return mainRouter.handle(request);
        }
        catch (Exception e){
            return ResponseFactory.createResponse(Status.BAD_REQUEST);
        }
    }
}
