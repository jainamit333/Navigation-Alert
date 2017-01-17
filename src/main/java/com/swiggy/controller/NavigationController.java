package com.swiggy.controller;

import com.swiggy.pojo.NavigationRequest;
import com.swiggy.service.NavigatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by amit on 16/1/17.
 */
@RestController
@RequestMapping("/")
public class NavigationController {

    @Autowired
    private NavigatorService navigatorService;

    @RequestMapping("/test")
    public String test(){
        return "I am test";
    }

    @RequestMapping(value = "/navigate",method = RequestMethod.POST)
    public void startNavigation(@RequestBody NavigationRequest navigationRequest){
         navigatorService.computeNavigation(navigationRequest);
    }
}
