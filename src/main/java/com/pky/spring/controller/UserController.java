package com.pky.spring.controller;

import com.pky.spring.domain.User;
import com.pky.spring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 3. 19..
 */
@RestController

public class UserController {

    public static final String URL_USER = "/user";
    public static final String URL_USER_CREATE = URL_USER + "/add/";

    @Autowired
    private IUserService userService;

    @RequestMapping(value = UserController.URL_USER_CREATE, method = RequestMethod.POST)
    public @ResponseBody User create(@RequestBody final User user){
        userService.save(user);
        return userService.findByName(user.getName());
    }



}
