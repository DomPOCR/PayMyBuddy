package com.opc.paymybuddy.service;

import com.opc.paymybuddy.dao.BankAccountDao;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.model.BankAccount;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.web.exceptions.DataMissingException;
import com.opc.paymybuddy.web.exceptions.DataNotExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    BankAccountDao bankAccountDao;
    @Autowired
    UserDao userDao;

    // Pour le log4j2
    static final Logger logger = LogManager.getLogger("Services");

    @Override
    public List<BankAccount> findAll() {
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

        BankAccount bankAccount = new BankAccount(addAccount.getIban(), addAccount.getBic(), addAccount.getBankName(), addAccount.getAccountName(),addAccount.getUser());
        Optional<User> userBank = userDao.findById(userId);

        /*
        if (userBank.isPresent()) {

            bankAccount.setUser(userBank.get());
            bankAccountDao.save(bankAccount);

            return bankAccount;
        } else {
            String mess = String.format("Creation failed : user %s not exist !!", userId);
            logger.info(mess);
            throw new DataNotExistException(mess);
        }
        */
        /* Equivalent en fonction Lambda */

        bankAccount.setUser(userBank.<DataNotExistException>orElseThrow(() -> {

            String mess = String.format("Creation failed : user %s not exist !!", userId);
            logger.info(mess);
            throw new DataNotExistException(mess);

        }));


        bankAccountDao.save(bankAccount);

        return bankAccount;

    }
}
