package com.opc.paymybuddy.web.controller;

import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    // Liste des users

    @GetMapping(path= "Users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers(){
        List<User> userList = userService.findAll();

    }

}
