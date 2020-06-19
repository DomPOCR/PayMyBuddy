package com.opc.paymybuddy.web.controller;

import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;


    // Liste des users

    @GetMapping(value = "Users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> listUsers() {
        List<User> userList = userDao.findAll();
        return userList;
    }

   // Liste des users par email

    @GetMapping(value = "Users/{email}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> listUsersByEmail(@PathVariable String email) {
        List<User> userList = userDao.findByEmail(email);
        return userList;

    }

}
