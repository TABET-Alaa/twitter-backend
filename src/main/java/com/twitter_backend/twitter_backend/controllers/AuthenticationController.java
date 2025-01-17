package com.twitter_backend.twitter_backend.controllers;


import com.twitter_backend.twitter_backend.exceptions.EmailAlreadyTakenException;
import com.twitter_backend.twitter_backend.modals.ApplicationUser;
import com.twitter_backend.twitter_backend.modals.RegistrationObject;
import com.twitter_backend.twitter_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler({EmailAlreadyTakenException.class})
    public ResponseEntity<String> handleEmailTaken(){
        return ResponseEntity<String>("The email you provided is already in use");
    }

    //go to http://localhost:8000/auth/register
    @PostMapping("/register")
        public ApplicationUser register(@RequestBody RegistrationObject ro) {

        return userService.registerUser(ro);
    }

    @PutMapping("/update/phone")
    public ApplicationUser updatePhoneNumber(@RequestBody LinkedHashMap<String, String> body){
        String username = body.get("username");
        String phone = body.get("phone");

        ApplicationUser user = userService.getUserByUsername(username);
        user.setPhone(phone);

        return userService.updateUser(user);
    }













}
