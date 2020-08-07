package com.opc.paymybuddy.UT_service;

import com.opc.paymybuddy.dao.BankAccountDao;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.model.BankAccount;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.service.BankAccountService;
import com.opc.paymybuddy.service.BankAccountServiceImpl;
import com.opc.paymybuddy.web.exceptions.DataMissingException;
import com.opc.paymybuddy.web.exceptions.DataNotExistException;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class BankAccountServiceTest {

    @Autowired
    BankAccountService bankAccountService;
    @MockBean
    BankAccountDao bankAccountDaoMock;
    @MockBean
    UserDao userDaoMock;
    Integer IdUserTest = 1;

    // Constantes pour le jeu de test
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
        Optional<User> userBank = Optional.of(userBankMock);

        BankAccount bankAccountTest = new BankAccount();

        bankAccountTest.setIban("IBANTEST");
        bankAccountTest.setBic("BICTEST");
        bankAccountTest.setBankName("AXA BANQUE");
        bankAccountTest.setAccountName("COMPTE PERSO");
        bankAccountTest.setUser(userBankMock);


        // WHEN
        Mockito.when(userDaoMock.findById(any(Integer.class))).thenReturn(userBank);
        Mockito.when((bankAccountDaoMock.save(bankAccountTest))).thenReturn(bankAccountTest);

        BankAccount newBankAccount = bankAccountService.addBankAccount(bankAccountTest,IdUserTest);

        // THEN
        assertNotNull(newBankAccount);
        verify(bankAccountDaoMock,Mockito.times(1)).save(newBankAccount);
    }

    @Test
    void addBankAccountNonExistingUser() throws Exception {

        // GIVEN
        User userBankMock = new User(99,lastNameTest,firstNameTest,emailTest,passwordTest,balanceTest,createDate);

        BankAccount bankAccountTest = new BankAccount();

        bankAccountTest.setIban("IBANTEST");
        bankAccountTest.setBic("BICTEST");
        bankAccountTest.setBankName("AXA BANQUE");
        bankAccountTest.setAccountName("COMPTE PERSO");
        bankAccountTest.setUser(userBankMock);

        Mockito.when(userDaoMock.existsById(any(Integer.class))).thenReturn(Boolean.FALSE);
        Mockito.when((bankAccountDaoMock.save(bankAccountTest))).thenReturn(bankAccountTest);

        // WHEN
        // THEN
        try{
            bankAccountService.addBankAccount(bankAccountTest,IdUserTest);
        } catch (DataNotExistException eExp){
            assert (eExp.getMessage().contains("not exist"));
        }
    }

    @Test
    void addBankAccountWithoutIban() throws Exception {

        // GIVEN
        User userBankMock = new User(IdUserTest,lastNameTest,firstNameTest,emailTest,passwordTest,balanceTest,createDate);

        BankAccount bankAccountTest = new BankAccount();

        bankAccountTest.setIban("");
        bankAccountTest.setBic("BICTEST");
        bankAccountTest.setBankName("AXA BANQUE");
        bankAccountTest.setAccountName("COMPTE PERSO");
        bankAccountTest.setUser(userBankMock);

        Mockito.when(userDaoMock.existsById(any(Integer.class))).thenReturn(Boolean.TRUE);
        Mockito.when((bankAccountDaoMock.save(bankAccountTest))).thenReturn(bankAccountTest);

        // WHEN
        // THEN
        try{
            bankAccountService.addBankAccount(bankAccountTest,IdUserTest);
        } catch (DataMissingException eExp){
            assert (eExp.getMessage().contains("iban is required"));
        }
    }

    @Test
    void addBankAccountWithoutBic() throws Exception {

        // GIVEN
        User userBankMock = new User(IdUserTest,lastNameTest,firstNameTest,emailTest,passwordTest,balanceTest,createDate);

        BankAccount bankAccountTest = new BankAccount();

        bankAccountTest.setIban("IBANTEST");
        bankAccountTest.setBic("");
        bankAccountTest.setBankName("AXA BANQUE");
        bankAccountTest.setAccountName("COMPTE PERSO");
        bankAccountTest.setUser(userBankMock);

        Mockito.when(userDaoMock.existsById(any(Integer.class))).thenReturn(Boolean.TRUE);
        Mockito.when((bankAccountDaoMock.save(bankAccountTest))).thenReturn(bankAccountTest);

        // WHEN
        // THEN
        try{
            bankAccountService.addBankAccount(bankAccountTest,IdUserTest);
        } catch (DataMissingException eExp){
            assert (eExp.getMessage().contains("bic is required"));
        }
    }

    @Test
    void addBankAccountWithoutBankName() throws Exception {

        // GIVEN
        User userBankMock = new User(IdUserTest,lastNameTest,firstNameTest,emailTest,passwordTest,balanceTest,createDate);

        BankAccount bankAccountTest = new BankAccount();

        bankAccountTest.setIban("IBANTEST");
        bankAccountTest.setBic("BICTEST");
        bankAccountTest.setBankName("");
        bankAccountTest.setAccountName("COMPTE PERSO");
        bankAccountTest.setUser(userBankMock);

        Mockito.when(userDaoMock.existsById(any(Integer.class))).thenReturn(Boolean.TRUE);
        Mockito.when((bankAccountDaoMock.save(bankAccountTest))).thenReturn(bankAccountTest);

        // WHEN
        // THEN
        try{
            bankAccountService.addBankAccount(bankAccountTest,IdUserTest);
        } catch (DataMissingException eExp){
            assert (eExp.getMessage().contains("Bank name is required"));
        }
    }

    @Test
    void addBankAccountWithoutAccountName() throws Exception {

        // GIVEN
        User userBankMock = new User(IdUserTest,lastNameTest,firstNameTest,emailTest,passwordTest,balanceTest,createDate);

        BankAccount bankAccountTest = new BankAccount();

        bankAccountTest.setIban("IBANTEST");
        bankAccountTest.setBic("BICTEST");
        bankAccountTest.setBankName("AXA BANQUE");
        bankAccountTest.setAccountName("");
        bankAccountTest.setUser(userBankMock);

        Mockito.when(userDaoMock.existsById(any(Integer.class))).thenReturn(Boolean.TRUE);
        Mockito.when((bankAccountDaoMock.save(bankAccountTest))).thenReturn(bankAccountTest);

        // WHEN
        // THEN
        try{
            bankAccountService.addBankAccount(bankAccountTest,IdUserTest);
        } catch (DataMissingException eExp){
            assert (eExp.getMessage().contains("Account name is required"));
        }
    }

    @TestConfiguration
    static class BankAccountTestsContextConfiguration {

        @Bean
        public BankAccountService userService() {
            return new BankAccountServiceImpl();
        }
    }
}