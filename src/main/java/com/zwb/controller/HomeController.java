package com.zwb.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = {"", "/home"}, method=RequestMethod.GET)
    public String home(){
        return "home";
    }
    /*
     * 添加@PreAuthorize标注，当我们访问这两个URL的时候，Spring Security会帮我们验证当前用户是否有权限访问该地址。
     * 
     * */
    @RequestMapping(value = "/helloadmin", method=RequestMethod.GET)
    @PreAuthorize("hasAnyRole('admin')")
    public String helloAdmin(){
        return "helloAdmin";
    }
    
    @RequestMapping(value = "/hellouser", method=RequestMethod.GET)
    @PreAuthorize("hasAnyRole('admin', 'user')")
    public String helloUser(){
        return "helloUser";
    }
    
    @RequestMapping(value = "/login", method=RequestMethod.GET)
    public String login(){
        return "login";
    }
    
    @RequestMapping("/403")
    public String forbidden(){
        return "403";
    }
}
