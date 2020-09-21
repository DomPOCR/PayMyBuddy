package com.opc.paymybuddy.dao;

import com.opc.paymybuddy.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.opc.paymybuddy.model.User;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

    User findByEmail(String email);
    User findUsersByListBankAccounts(Optional<BankAccount> bankAccount);
    boolean existsByEmail(String email);
    boolean existsByListBankAccounts(Optional<BankAccount> bankAccount);


}
