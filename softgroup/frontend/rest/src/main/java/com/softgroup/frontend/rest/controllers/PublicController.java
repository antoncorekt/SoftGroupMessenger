package com.softgroup.frontend.rest.controllers;

import com.softgroup.common.protocol.*;
import com.softgroup.common.protocol.ResponseStatus;
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
           // if (!request.getHeader().getType().equals("authorization")){
           //     return new Response<ResponseData>(null,null,new ResponseStatus(400,"Invalid param"));
           // }
            return mainRouter.handle(request);
        }
        catch (Exception e){
            return new Response<ResponseData>(null,null,new ResponseStatus(400,"Bad request"));
        }
    }


}
