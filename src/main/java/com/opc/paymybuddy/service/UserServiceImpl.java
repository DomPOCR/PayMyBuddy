package com.opc.paymybuddy.service;

import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.web.exceptions.DataAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User addUser(User addUser) {

        if (userDao.findByEmail(addUser.getEmail()) == null) {
            userDao.save(addUser);

        } else {
            throw new DataAlreadyExistException(
                    "Le mail " + addUser.getEmail() + " existe déjà !!");
        }
        return addUser;
    }
}
