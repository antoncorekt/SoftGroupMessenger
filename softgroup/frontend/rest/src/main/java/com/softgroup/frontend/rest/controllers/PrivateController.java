package com.softgroup.frontend.rest.controllers;

import com.softgroup.common.protocol.*;
import com.softgroup.common.protocol.utils.ResponseFactory;
import com.softgroup.common.protocol.utils.Status;
import com.softgroup.common.router.api.CommonRouterHandler;
import com.softgroup.common.router.api.Handler;
import com.softgroup.common.router.impl.MainRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by anton on 29.03.17.
 */
@RestController
@RequestMapping(path = "/root",
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class PrivateController {

    @Autowired
    private MainRouter mainRouter;

    @RequestMapping(path = "/private")
    public Response<?> getPrivateMessage(@RequestBody Request<?> request){
        try {
            request.setRoutedData((RoutedData)(SecurityContextHolder.getContext().getAuthentication()).getPrincipal());
            return mainRouter.handle(request);
        } catch (Exception e) {
            return ResponseFactory.createResponse(Status.BAD_REQUEST);
        }
    }
}
