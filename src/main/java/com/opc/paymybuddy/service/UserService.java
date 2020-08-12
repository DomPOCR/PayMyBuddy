package com.opc.paymybuddy.service;

import com.opc.paymybuddy.dto.UserDto;
import com.opc.paymybuddy.model.User;

import java.util.List;


public interface UserService {

    List<User> findAll();
    long count();
    User findByEmail(String email);


    boolean addUser(UserDto newUser) throws Exception;
    boolean connectUser(UserDto userDto) throws Exception;

    User addBuddy(User newBuddy, Integer userId) throws Exception;

}
