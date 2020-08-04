package com.opc.paymybuddy.web.controller;

import com.opc.paymybuddy.dao.BankAccountDao;
import com.opc.paymybuddy.model.BankAccount;
import com.opc.paymybuddy.service.BankAccountService;

import com.opc.paymybuddy.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

@RestController
public class BankAccountController {

    @Autowired
    private BankAccountDao bankAccountDao;

    @Autowired
    private BankAccountService bankAccountService;

    // Pour le log4j2
    static final Logger logger = LogManager.getLogger("Controlleur");


    // Liste des comptes

    @GetMapping("/Accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<BankAccount> ListBankAccount() {

        return bankAccountDao.findAll();
    }

    // Ajout d'un compte

    @PostMapping("/AddAccount/{userId}")
    public ResponseEntity <BankAccount> AddAccount(@RequestBody BankAccount addAccount, @PathVariable Integer userId ) throws Exception {

        BankAccount bankAccountAdd = bankAccountService.addBankAccount(addAccount, userId);
        logger.info("Add bank account for userid %s OK",userId);
        return new ResponseEntity(bankAccountAdd, HttpStatus.CREATED);

    }
}
