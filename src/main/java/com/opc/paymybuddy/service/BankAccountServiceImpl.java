package com.opc.paymybuddy.service;

import com.opc.paymybuddy.dao.BankAccountDao;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.dto.BankAccountDto;
import com.opc.paymybuddy.model.BankAccount;
import com.opc.paymybuddy.model.User;

import com.opc.paymybuddy.web.exceptions.DataMissingException;
import com.opc.paymybuddy.web.exceptions.DataNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    BankAccountDao bankAccountDao;
    @Autowired
    UserDao userDao;

    // Pour le log4j2
    static final Logger logger = LogManager.getLogger("Services");

    @Override
    public List<BankAccount> findAll(){
        return bankAccountDao.findAll();
    }

    @Override
    public BankAccount addBankAccount(BankAccount addAccount, Integer userId) throws Exception {

        if (addAccount.getIban().isEmpty()) {
            logger.error("Creation account : KO");
            throw new DataMissingException("Creation failed : iban is required !!");
        }
        if (addAccount.getBic().isEmpty()) {
            logger.error("Creation account : KO");
            throw new DataMissingException("Creation failed : bic is required !!");
        }
        if (addAccount.getBankName().isEmpty()) {
            logger.error("Creation account : KO");
            throw new DataMissingException(" Creation failed : Bank name is required !!");
        }
        if (addAccount.getAccountName().isEmpty()) {
            logger.error("Creation account : KO");
            throw new DataMissingException("Creation failed : Account name is required !!");
        }

        if (!userDao.existsById(userId)) {  // user inexistant

            String mess = String.format("Creation failed : user %s not exist !!", userId);

            logger.info(mess);

            throw new DataNotExistException(mess);

        }
        BankAccount bankAccount = new BankAccount(addAccount.getIban(), addAccount.getBic(), addAccount.getBankName(), addAccount.getAccountName());

        Optional<User> user = userDao.findById(userId);
        User userBank = new User();

        userBank.setId(userId);
        userBank.setLastname(user.get().getLastname());
        userBank.setFirstname(user.get().getFirstname());
        userBank.setEmail(user.get().getEmail());
        userBank.setPassword(user.get().getPassword());
        userBank.setCreateDate(user.get().getCreateDate());
        userBank.setBalance(user.get().getBalance());

        bankAccount.setUser(userBank);

        bankAccountDao.save(bankAccount);

        return bankAccount;
    }
}
