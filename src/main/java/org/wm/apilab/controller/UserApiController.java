package org.wm.apilab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.wm.apilab.annotation.Authorization;
import org.wm.apilab.model.SysUser;
import org.wm.apilab.service.UserService;
import org.wm.apilab.utils.JSONUtils;

@RestController
@RequestMapping({"/userApi","/home/userApi"})
@SuppressWarnings("rawtypes")
public class UserApiController {

    @Autowired
    private UserService userService;

//    @RequestMapping(value = "/api/login", method = RequestMethod.PUT)
//    @ResponseBody
//    public ResponseEntity login(@RequestBody SysUser user){
//        return userService.login(user);
//    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity register(@RequestBody SysUser user){
        return userService.register(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Authorization
    @ResponseBody
    public ResponseEntity getUser(@PathVariable int id) {
        return userService.getUser(id);
//        reutrn JSONUtils.fillResultString(id, null, id);
    }
    
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String sayWorld(@PathVariable("name") String name) {
        return "Hello " + name;
    }
    
}
