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
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    BankAccountDao bankAccountDao;
    @Autowired
    BankAccountService bankAccountService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(User user) {

        BankAccount newBankAccount = null;

        if (!userDao.existsByEmail(user.getEmail())) {

           /*
            newBankAccount = bankAccountService.addBankAccount(user.getListBankAccounts().get(user.getId()));

            bankAccountDao.save(newBankAccount);
            */
            userDao.save(user);

        } else {
            String mess= String.format("Le mail %s existe déjà !!", user.getEmail());
            throw new DataAlreadyExistException(mess);
        }
        return user;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
/*
    public List<User> findBuddyByUser(User user) {
        return userDao.findByEmail(user.getEmail()).getListBuddy();
    }
*/
    public User addBuddy(User newBuddy) {
        return null;
    }

}
