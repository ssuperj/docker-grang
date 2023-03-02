package com.grang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/auth/login")
    public String loginForm() {
        return "/login";
    }

    @GetMapping("/auth/signup")
    public String signup() {
        return "signup";
    }
    @GetMapping("/updateUserForm")
    public String updateUserForm() {
        return "/updateUserForm";
    }
}
