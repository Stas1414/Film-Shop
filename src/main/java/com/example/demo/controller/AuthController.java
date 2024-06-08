package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String loginPage(){
        return "auth/authorization";
    }

    @GetMapping("/checkedBlock")
    public String blockPage(){
        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        boolean checkBlock = user.isLocked();
        if (!checkBlock){
            return "redirect:/user/films";
        }
        else {
            return "redirect:/auth/block";
        }
    }

    @GetMapping("/block")
    public String block(){
        return "auth/block";
    }
}
