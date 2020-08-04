package com.opc.paymybuddy.service;

import com.opc.paymybuddy.model.BankAccount;

public interface BankAccountService {

     BankAccount  addBankAccount(BankAccount addAccount, Integer userId) throws Exception;

}
