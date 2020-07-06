package com.opc.paymybuddy.service;

import com.opc.paymybuddy.dao.BankAccountDao;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.model.BankAccount;
import com.opc.paymybuddy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    BankAccountDao bankAccountDao;
    @Autowired
    UserDao userDao;

    public BankAccount addBankAccount(BankAccount bankAccount) {
        return bankAccount;
    }
}
