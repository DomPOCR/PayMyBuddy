package com.opc.paymybuddy.service;

import com.opc.paymybuddy.dao.BankAccountDao;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.model.BankAccount;
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
    @Autowired
    BankAccountDao bankAccountDao;

    @Override
    public User addUser(User addUser) {

        User newUser=null;
        BankAccount newBankAccount=null;

        if (!userDao.existsByEmail(addUser.getEmail())) {

       /*     newUser.setFirstname(addUser.getFirstname());
            newUser.setLastname(addUser.getLastname());
            newUser.setEmail(addUser.getEmail());
            newUser.setPassword(addUser.getPassword());
            newUser.setBalance(addUser.getBalance());
            newUser.setActive(addUser.isActive());

            newUser.setBankAccounts(addUser.getBankAccounts());
*/
            userDao.save(addUser);


        } else {
            throw new DataAlreadyExistException(
                    "Le mail " + addUser.getEmail() + " existe déjà !!");
        }
        return addUser;
    }
}
