package com.opc.paymybuddy.web.controller;

import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    // Liste des users

    @GetMapping("/Users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> listUsers() {
        return userDao.findAll();
    }

    // Liste des users par email

    @GetMapping("/Users/{email}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> listUsersByEmail(@PathVariable String email) {
        return userDao.findByEmail(email);
    }

    // Comptage des users

    @GetMapping("/UsersCount")
    @ResponseStatus(HttpStatus.OK)
    public long UsersCount() {
        return userDao.count();
    }

    // Ajout d'un user

    @PostMapping("/AddUser")
    @ResponseStatus(HttpStatus.CREATED)
    /*public User addUser(@RequestBody @Valid User addUser) {

        User MyUser = userService.addUser(addUser);
        return addUser;
    }
    */
    public String addUser(@RequestBody @Valid User addUser) {
        return "USER OK";
    }
}

