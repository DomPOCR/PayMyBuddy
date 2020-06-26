package com.opc.paymybuddy.service;

import com.opc.paymybuddy.dao.BankAccountDao;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.model.BankAccount;
import com.opc.paymybuddy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    BankAccountDao bankAccountDao;
    @Autowired
    UserDao userDao;

    @Override
    public BankAccount addBankAccount(BankAccount bankAccount, User user) {

        BankAccount newBankAccount = null;
        BankAccount addBankAccount = new BankAccount(bankAccount.getIban(), bankAccount.getBic(),
                bankAccount.getBankName(), bankAccount.getAccountName(), user);
        newBankAccount = bankAccountDao.save(addBankAccount);

        return newBankAccount;

    }

}
