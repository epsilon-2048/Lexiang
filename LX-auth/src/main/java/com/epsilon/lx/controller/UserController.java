/**
 *
 */
package com.epsilon.lx.controller;

import com.epsilon.lx.exception.NotAcceptableException;
import com.epsilon.lx.exception.NotFoundException;
import com.epsilon.lx.service.MyUserDetailService;
import epsilon_2048.security.browser.support.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    MyUserDetailService userDetailService;

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        //public Object getCurrentUser(Authentication authentication){
        //	return authentication;
        //System.out.println(user);
        return user;
    }

    @PostMapping("/register")
    public SimpleResponse register(@RequestParam("username") String username,
                                   @RequestParam("password") String password, @RequestParam(name = "imageCode", required=false) String imageCode) throws NotFoundException, NotAcceptableException {
        String[] strings = {"Spectator"};
        userDetailService.addUser(username, password, strings, imageCode);
        return new SimpleResponse("注册成功");
    }
}
