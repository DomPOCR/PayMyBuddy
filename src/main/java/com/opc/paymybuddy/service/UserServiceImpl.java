package com.opc.paymybuddy.service;

import com.opc.paymybuddy.dao.BankAccountDao;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.model.BankAccount;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.web.exceptions.DataAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    BankAccountDao bankAccountDao;
    @Autowired
    BankAccountService bankAccountService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User addUser(User user) {

        User newUser = null;
        BankAccount newBankAccount = null;

        if (!userDao.existsByEmail(user.getEmail())) {

          // newBankAccount = bankAccountService.addBankAccount(user.getListBankAccounts().get(user.getId()),user);

            userDao.save(user);
        }

        else {
            throw new DataAlreadyExistException(
                    "Le mail " + user.getEmail() + " existe déjà !!");
        }
        return user;
    }
}
