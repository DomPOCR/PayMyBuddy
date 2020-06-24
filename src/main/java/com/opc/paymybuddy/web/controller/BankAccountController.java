package com.opc.paymybuddy.web.controller;

import com.opc.paymybuddy.dao.BankAccountDao;
import com.opc.paymybuddy.model.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankAccountController {

    @Autowired
    private BankAccountDao bankAccountDao;

    // Liste des comptes

    @GetMapping("/Accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<BankAccount> ListBankAccount() {
        return bankAccountDao.findAll();
    }
}
