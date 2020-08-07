package com.opc.paymybuddy.UT_service;

import com.opc.paymybuddy.dao.BankAccountDao;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.dto.UserDto;
import com.opc.paymybuddy.model.BankAccount;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.service.BankAccountService;
import com.opc.paymybuddy.service.BankAccountServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.util.DateUtil.now;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class BankAccountServiceTest {

    @TestConfiguration
    static class BankAccountTestsContextConfiguration {

        @Bean
        public BankAccountService userService() {
            return new BankAccountServiceImpl();
        }
    }

    @Autowired
    BankAccountService bankAccountService;

    @MockBean
    BankAccountDao bankAccountDaoMock;

    @MockBean
    UserDao userDaoMock;

    // Constantes pour le jeu de test

    Integer IdUserTest = 1;
    String lastNameTest = "NomTest01";
    String firstNameTest = "PrenomTest01";
    String emailTest = "EmailTest01@mail.com";
    String passwordTest = "pwd01";
    BigDecimal balanceTest = BigDecimal.valueOf(0);
    Date createDate = now();

    @Test
    void addBankAccountExistingUser() throws Exception {

        // GIVEN
        User userBankMock = new User(IdUserTest,lastNameTest,firstNameTest,emailTest,passwordTest,balanceTest,createDate);

        BankAccount bankAccountTest = new BankAccount();

        bankAccountTest.setIban("IBANTEST");
        bankAccountTest.setBic("BICTEST");
        bankAccountTest.setBankName("AXA BANQUE");
        bankAccountTest.setAccountName("COMPTE PERSO");
        bankAccountTest.setUser(userBankMock);

        Optional<User> userBank = Optional.of(new User());

        //Mockito.when(userDaoMock.existsById(any(Integer.class))).thenReturn(Boolean.TRUE);
        //Mockito.when((bankAccountDaoMock.save(bankAccountTest))).thenReturn(bankAccountTest);

        Mockito.when(userDaoMock.findById(any(Integer.class))).thenReturn(userBank);
        Mockito.when((bankAccountDaoMock.save(bankAccountTest))).thenReturn(bankAccountTest);

        // WHEN
        BankAccount newBankAccount = bankAccountService.addBankAccount(bankAccountTest,IdUserTest);

        // THEN
        assert (newBankAccount.getIban().equals(bankAccountTest.getIban()));

        assert (newBankAccount.getUser().getId()).equals(IdUserTest);
        assert(newBankAccount.getUser().getFirstname()).equals(firstNameTest);
        assert(newBankAccount.getUser().getLastname()).equals(lastNameTest);
    }
}