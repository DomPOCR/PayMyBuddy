package com.opc.paymybuddy.service;

import com.opc.paymybuddy.model.User;

import java.util.List;


public interface UserService {

    public User addUser(User newUser) throws Exception;
    public List<User> findAll();
    public List<User> findBuddyByUser(User user);
    public User addBuddy(User newBuddy);

}
