package com.twitter_backend.twitter_backend.controllers;


import com.twitter_backend.twitter_backend.modals.ApplicationUser;
import com.twitter_backend.twitter_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    //go to http://localhost:8000/auth/register
    @PostMapping("/register")
        public ApplicationUser register(@RequestBody ApplicationUser user) {
        return userService.registerUser(user);
    }














}
