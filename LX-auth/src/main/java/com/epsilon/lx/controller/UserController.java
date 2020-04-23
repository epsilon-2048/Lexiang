/**
 *
 */
package com.epsilon.lx.controller;

import com.epsilon.lx.exception.NotAcceptableException;
import com.epsilon.lx.exception.NotFoundException;
import com.epsilon.lx.service.MyUserDetailService;
import epsilon_2048.security.browser.support.SimpleResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("auth/user")
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
    @ApiOperation(value="测试抛出异常", notes="已测试")
    @RequestMapping(value = "/url", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getURL(HttpServletRequest request){
        return request.getRequestURL().toString();
    }
}
