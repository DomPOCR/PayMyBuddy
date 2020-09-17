package com.opc.paymybuddy.UT_service;

import com.opc.paymybuddy.dao.*;
import com.opc.paymybuddy.dto.ExternalTransfertDto;
import com.opc.paymybuddy.dto.InternalTransfertDto;
import com.opc.paymybuddy.model.BankAccount;
import com.opc.paymybuddy.model.Relation;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.service.TransfertService;
import com.opc.paymybuddy.service.TransfertServiceImpl;
import com.opc.paymybuddy.web.exceptions.DataIncorrectException;
import com.opc.paymybuddy.web.exceptions.DataNotExistException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.util.DateUtil.now;

@ExtendWith(SpringExtension.class)
public class TransfertServiceTest {

    @TestConfiguration
    static class TransfertServiceTestsContextConfiguration {

        @Bean
        public TransfertService transfertService() {
            return new TransfertServiceImpl();
        }
    }

    @Autowired
    TransfertService transfertService;

    @MockBean
    TransfertDao transfertDaoMock;

    @MockBean
    InternalTransfertDao internalTransfertDaoMock;

    @MockBean
    ExternalTransfertDao externalTransfertDaoMock;

    @MockBean
    UserDao userDaoMock;

    @MockBean
    BankAccountDao bankAccountDaoMock;


    String lastNameTest1 = "NomTest01";
    String firstNameTest1 = "PrenomTest01";
    String emailUserTest1 = "jb01@email.com";
    String passwordTest1 = "pwd01";
    BigDecimal balanceTest1 = BigDecimal.valueOf(100);
    Date createDate1 = now();

    String lastNameTest2 = "NomTest02";
    String firstNameTest2 = "PrenomTest02";
    String emailUserTest2 = "jb02@email.com";
    String passwordTest2 = "pwd02";
    BigDecimal balanceTest2 = BigDecimal.valueOf(50);
    Date createDate2 = now();

    // ************************ INTERNAL TRANSFERTS ****************************//

    @Test // Cas passant
    public void newInternalTransfertWithCorrectInformations() throws Exception {

        // GIVEN

        // Constantes pour le jeu de test
        Integer senderId = 1;
        Integer receiverId = 4;
        BigDecimal amount = BigDecimal.valueOf(5);
        String description = "Remboursement café";

        InternalTransfertDto internalTransfertTest = new InternalTransfertDto(senderId, receiverId, amount, description);

        User userReceiver = new User(firstNameTest1, lastNameTest1, emailUserTest1, passwordTest1, balanceTest1, createDate1);
        User userSender = new User(firstNameTest2, lastNameTest2, emailUserTest2, passwordTest2, balanceTest2, createDate2);

        userSender.setId(senderId);
        userReceiver.setId(receiverId);

        Relation myBuddy = new Relation(userSender, userReceiver);
        List<Relation> listRelation = new ArrayList();
        listRelation.add(myBuddy);
        userSender.setListRelations(listRelation);

        // WHEN
        Mockito.when(userDaoMock.findById(receiverId)).thenReturn(Optional.of(userReceiver));
        Mockito.when(userDaoMock.findById(senderId)).thenReturn(Optional.of(userSender));

        InternalTransfertDto internalTransfert = transfertService.transfertBuddy(internalTransfertTest);

        // THEN
        Assertions.assertNotNull(internalTransfert);

    }

    @Test // Cas non passant
    public void newInternalTransfertWithNonExistingSenderId()  {

        // GIVEN

        // Constantes pour le jeu de test
        Integer senderId = 99;
        Integer receiverId = 1;
        BigDecimal amount = BigDecimal.valueOf(5);
        String description = "Remboursement café";

        InternalTransfertDto internalTransfertTest = new InternalTransfertDto(senderId, receiverId, amount, description);

        User userReceiver = new User(firstNameTest1, lastNameTest1, emailUserTest1, passwordTest1, balanceTest1, createDate1);
        User userSender = new User(firstNameTest2, lastNameTest2, emailUserTest2, passwordTest2, balanceTest2, createDate2);

        Relation myBuddy = new Relation(userSender, userReceiver);

        List<Relation> listRelation = new ArrayList();
        listRelation.add(myBuddy);
        userSender.setListRelations(listRelation);

        // WHEN
        Mockito.when(userDaoMock.findById(receiverId)).thenReturn(Optional.of(userReceiver));
        Mockito.when(userDaoMock.findById(senderId)).thenReturn(Optional.empty());

        // THEN
        // En remplacement du try / catch
        DataNotExistException e = Assert.assertThrows(DataNotExistException.class,()->transfertService.transfertBuddy(internalTransfertTest) );
        assert (e.getMessage().contains("senderId 99 does not exist"));
        /*try {
            InternalTransfertDto internalTransfert = transfertService.transfertBuddy(internalTransfertTest);
        } catch (DataNotExistException eExp) {
            assert (eExp.getMessage().contains("senderId 99 does not exist"));
        }*/

    }
    @Test // Cas non passant
    public void newInternalTransfertWithNonExistingReceiverId() {

        // GIVEN

        // Constantes pour le jeu de test
        Integer senderId = 1;
        Integer receiverId = 99;
        BigDecimal amount = BigDecimal.valueOf(5);
        String description = "Remboursement café";

        InternalTransfertDto internalTransfertTest = new InternalTransfertDto(senderId, receiverId, amount, description);

        User userReceiver = new User(firstNameTest1, lastNameTest1, emailUserTest1, passwordTest1, balanceTest1, createDate1);
        User userSender = new User(firstNameTest2, lastNameTest2, emailUserTest2, passwordTest2, balanceTest2, createDate2);

        Relation myBuddy = new Relation(userSender, userReceiver);

        List<Relation> listRelation = new ArrayList();
        listRelation.add(myBuddy);
        userSender.setListRelations(listRelation);

        // WHEN
        Mockito.when(userDaoMock.findById(receiverId)).thenReturn(Optional.empty());
        Mockito.when(userDaoMock.findById(senderId)).thenReturn(Optional.of(userSender));

        // THEN
        DataNotExistException e = Assert.assertThrows(DataNotExistException.class,()->transfertService.transfertBuddy(internalTransfertTest) );
        assert (e.getMessage().contains("receiverId " + receiverId + " does not exist"));
    }

    @Test // Cas non passant
    public void newInternalTransfertWithInsufficientBalance() throws Exception {

        // GIVEN

        // Constantes pour le jeu de test
        Integer senderId = 1;
        Integer receiverId = 4;
        BigDecimal amount = BigDecimal.valueOf(50000);
        String description = "Remboursement café";

        InternalTransfertDto internalTransfertTest = new InternalTransfertDto(senderId, receiverId, amount, description);

        User userReceiver = new User(firstNameTest1, lastNameTest1, emailUserTest1, passwordTest1, balanceTest1, createDate1);
        User userSender = new User(firstNameTest2, lastNameTest2, emailUserTest2, passwordTest2, balanceTest2, createDate2);

        userSender.setId(senderId);
        userReceiver.setId(receiverId);

        Relation myBuddy = new Relation(userSender, userReceiver);
        List<Relation> listRelation = new ArrayList();
        listRelation.add(myBuddy);
        userSender.setListRelations(listRelation);

        // WHEN
        Mockito.when(userDaoMock.findById(receiverId)).thenReturn(Optional.of(userReceiver));
        Mockito.when(userDaoMock.findById(senderId)).thenReturn(Optional.of(userSender));

        // THEN
        DataIncorrectException e = Assert.assertThrows(DataIncorrectException.class,()->transfertService.transfertBuddy(internalTransfertTest) );
        assert (e.getMessage().contains("Insufficient balance"));
    }
    @Test // Cas non passant
    public void newInternalTransfertWithSameId() {

        // GIVEN

        // Constantes pour le jeu de test
        Integer senderId = 1;
        Integer receiverId = 1;
        BigDecimal amount = BigDecimal.valueOf(5);
        String description = "Remboursement café";

        InternalTransfertDto internalTransfertTest = new InternalTransfertDto(senderId, receiverId, amount, description);

        User userReceiver = new User(firstNameTest1, lastNameTest1, emailUserTest1, passwordTest1, balanceTest1, createDate1);
        User userSender = new User(firstNameTest2, lastNameTest2, emailUserTest2, passwordTest2, balanceTest2, createDate2);

        userSender.setId(senderId);
        userReceiver.setId(receiverId);

        Relation myBuddy = new Relation(userSender, userReceiver);
        List<Relation> listRelation = new ArrayList();
        listRelation.add(myBuddy);
        userSender.setListRelations(listRelation);

        // WHEN
        Mockito.when(userDaoMock.findById(receiverId)).thenReturn(Optional.of(userReceiver));
        Mockito.when(userDaoMock.findById(senderId)).thenReturn(Optional.of(userSender));

        // THEN
        DataIncorrectException e = Assert.assertThrows(DataIncorrectException.class,()->transfertService.transfertBuddy(internalTransfertTest) );
        assert (e.getMessage().contains("senderId and receiverId cannot be the same"));
    }

    @Test // Cas non passant
    public void newInternalTransfertWithNegativeBalance() {

        // GIVEN

        // Constantes pour le jeu de test
        Integer senderId = 1;
        Integer receiverId = 4;
        BigDecimal amount = BigDecimal.valueOf(-10);
        String description = "Remboursement café";

        InternalTransfertDto internalTransfertTest = new InternalTransfertDto(senderId, receiverId, amount, description);

        User userReceiver = new User(firstNameTest1, lastNameTest1, emailUserTest1, passwordTest1, balanceTest1, createDate1);
        User userSender = new User(firstNameTest2, lastNameTest2, emailUserTest2, passwordTest2, balanceTest2, createDate2);

        userSender.setId(senderId);
        userReceiver.setId(receiverId);

        Relation myBuddy = new Relation(userSender, userReceiver);
        List<Relation> listRelation = new ArrayList();
        listRelation.add(myBuddy);
        userSender.setListRelations(listRelation);

        // WHEN
        Mockito.when(userDaoMock.findById(receiverId)).thenReturn(Optional.of(userReceiver));
        Mockito.when(userDaoMock.findById(senderId)).thenReturn(Optional.of(userSender));

        // THEN
        DataIncorrectException e = Assert.assertThrows(DataIncorrectException.class,()->transfertService.transfertBuddy(internalTransfertTest) );
        assert (e.getMessage().contains("the amount to be credited must be greater than 0"));
    }

    @Test // Cas non passant
    public void newInternalTransfertWithNullBalance() {

        // GIVEN

        // Constantes pour le jeu de test
        Integer senderId = 1;
        Integer receiverId = 4;
        BigDecimal amount = BigDecimal.valueOf(0);
        String description = "Remboursement café";

        InternalTransfertDto internalTransfertTest = new InternalTransfertDto(senderId, receiverId, amount, description);

        User userReceiver = new User(firstNameTest1, lastNameTest1, emailUserTest1, passwordTest1, balanceTest1, createDate1);
        User userSender = new User(firstNameTest2, lastNameTest2, emailUserTest2, passwordTest2, balanceTest2, createDate2);

        userSender.setId(senderId);
        userReceiver.setId(receiverId);

        Relation myBuddy = new Relation(userSender, userReceiver);
        List<Relation> listRelation = new ArrayList();
        listRelation.add(myBuddy);
        userSender.setListRelations(listRelation);

        // WHEN
        Mockito.when(userDaoMock.findById(receiverId)).thenReturn(Optional.of(userReceiver));
        Mockito.when(userDaoMock.findById(senderId)).thenReturn(Optional.of(userSender));

        // THEN
        DataIncorrectException e = Assert.assertThrows(DataIncorrectException.class,()->transfertService.transfertBuddy(internalTransfertTest) );
        assert (e.getMessage().contains("the amount to be credited must be greater than 0"));
    }

    @Test // Cas non passant
    public void newInternalTransfertWithBuddyNotInListBuddy() {

        // GIVEN

        // Constantes pour le jeu de test
        Integer senderId = 1;
        Integer receiverId = 5;
        BigDecimal amount = BigDecimal.valueOf(5);
        String description = "Remboursement café";

        InternalTransfertDto internalTransfertTest = new InternalTransfertDto(senderId, receiverId, amount, description);

        User userReceiver = new User(firstNameTest1, lastNameTest1, emailUserTest1, passwordTest1, balanceTest1, createDate1);
        User userSender = new User(firstNameTest2, lastNameTest2, emailUserTest2, passwordTest2, balanceTest2, createDate2);

        userSender.setId(senderId);
        userReceiver.setId(receiverId);

        Relation myBuddy = new Relation(userSender, userReceiver);
        List<Relation> listRelation = new ArrayList();
        //On n'ajoute pas le buddy à la liste (liste vide)
        userSender.setListRelations(listRelation);

        // WHEN
        Mockito.when(userDaoMock.findById(receiverId)).thenReturn(Optional.of(userReceiver));
        Mockito.when(userDaoMock.findById(senderId)).thenReturn(Optional.of(userSender));

        // THEN
        DataNotExistException e = Assert.assertThrows(DataNotExistException.class,()->transfertService.transfertBuddy(internalTransfertTest) );
        assert (e.getMessage().contains("Add it before making a transfer"));
    }

    // ************************ EXTERNAL TRANSFERTS ****************************//

    @Test // Cas passant
    public void newExternalTransfertWithCorrectInformations()  {

        // GIVEN

        // Constantes pour le jeu de test
        Integer userId = 2;
        String iban = "IBANBAUER";
        BigDecimal amount = BigDecimal.valueOf(-500);
        String description = "Virement VERS ma banque";

        balanceTest2 = BigDecimal.valueOf(5000);

        ExternalTransfertDto transfertBankTest = new ExternalTransfertDto(userId,iban,amount);
        User userTest = new User(firstNameTest2, lastNameTest2, emailUserTest2, passwordTest2, balanceTest2, createDate2);
        BankAccount bankAccountTest = new BankAccount();

        userTest.setId(userId);
        bankAccountTest.setIban(iban);

        // WHEN
        Mockito.when(userDaoMock.findById(userId)).thenReturn(Optional.of(userTest));
        Mockito.when(bankAccountDaoMock.findByIban(iban)).thenReturn(bankAccountTest);

        ExternalTransfertDto externalTransfert = transfertService.transfertBank(transfertBankTest);

        // THEN
        Assertions.assertNotNull(externalTransfert);

    }

    @Test // Cas non passant
    public void newExternalTransfertWithNonExistingUserId() {

        // GIVEN

        // Constantes pour le jeu de test
        Integer userId = 99;
        String iban = "IBANBAUER";
        BigDecimal amount = BigDecimal.valueOf(-500);
        String description = "Virement VERS ma banque";

        balanceTest2 = BigDecimal.valueOf(5000);

        ExternalTransfertDto transfertBankTest = new ExternalTransfertDto(userId,iban,amount);
        User userTest = new User(firstNameTest2, lastNameTest2, emailUserTest2, passwordTest2, balanceTest2, createDate2);
        BankAccount bankAccountTest = new BankAccount();

        userTest.setId(userId);
        bankAccountTest.setIban(iban);

        // WHEN
        Mockito.when(userDaoMock.findById(userId)).thenReturn(Optional.empty());
        Mockito.when(bankAccountDaoMock.findByIban(iban)).thenReturn(bankAccountTest);

        // THEN
        DataNotExistException e = Assert.assertThrows(DataNotExistException.class,()->transfertService.transfertBank(transfertBankTest) );
        assert (e.getMessage().contains("Transfert failed : user Id " + userId + " does not exist"));

    }
    @Test // Cas non passant
    public void newExternalTransfertWithNonExistingIban() {

        // GIVEN

        // Constantes pour le jeu de test
        Integer userId = 2;
        String iban = "IBANBAUERBAD";
        BigDecimal amount = BigDecimal.valueOf(-500);
        String description = "Virement VERS ma banque";

        balanceTest2 = BigDecimal.valueOf(5000);

        ExternalTransfertDto transfertBankTest = new ExternalTransfertDto(userId,iban,amount);
        User userTest = new User(firstNameTest2, lastNameTest2, emailUserTest2, passwordTest2, balanceTest2, createDate2);
        BankAccount bankAccountTest = new BankAccount();

        userTest.setId(userId);
        bankAccountTest.setIban(iban);

        // WHEN
        Mockito.when(userDaoMock.findById(userId)).thenReturn(Optional.of(userTest));
        Mockito.when(bankAccountDaoMock.findByIban(iban)).thenReturn(null);

       // THEN
        DataNotExistException e = Assert.assertThrows(DataNotExistException.class,()->transfertService.transfertBank(transfertBankTest) );
        assert (e.getMessage().contains("Transfert failed : IBAN " + iban + " does not exist for this user Id"));

    }
    @Test // Cas non passant
    public void newExternalTransfertWithAmountZero() {

        // GIVEN

        // Constantes pour le jeu de test
        Integer userId = 2;
        String iban = "IBANBAUER";
        BigDecimal amount = BigDecimal.valueOf(0);
        String description = "Virement VERS ma banque";

        balanceTest2 = BigDecimal.valueOf(5000);

        ExternalTransfertDto transfertBankTest = new ExternalTransfertDto(userId,iban,amount);
        User userTest = new User(firstNameTest2, lastNameTest2, emailUserTest2, passwordTest2, balanceTest2, createDate2);
        BankAccount bankAccountTest = new BankAccount();

        userTest.setId(userId);
        bankAccountTest.setIban(iban);

        // WHEN
        Mockito.when(userDaoMock.findById(userId)).thenReturn(Optional.of(userTest));
        Mockito.when(bankAccountDaoMock.findByIban(iban)).thenReturn(bankAccountTest);

        // THEN
        DataIncorrectException e = Assert.assertThrows(DataIncorrectException.class,()->transfertService.transfertBank(transfertBankTest) );
        assert (e.getMessage().contains("Transfert failed : the amount cannot be equal to 0"));

    }

    @Test // Cas non passant
    public void newExternalTransfertWithInsufficientBalance() {

        // GIVEN

        // Constantes pour le jeu de test
        Integer userId = 2;
        String iban = "IBANBAUER";
        BigDecimal amount = BigDecimal.valueOf(-500);
        String description = "Virement VERS ma banque";

        balanceTest2 = BigDecimal.valueOf(400);

        ExternalTransfertDto transfertBankTest = new ExternalTransfertDto(userId,iban,amount);
        User userTest = new User(firstNameTest2, lastNameTest2, emailUserTest2, passwordTest2, balanceTest2, createDate2);
        BankAccount bankAccountTest = new BankAccount();

        userTest.setId(userId);
        bankAccountTest.setIban(iban);

        // WHEN
        Mockito.when(userDaoMock.findById(userId)).thenReturn(Optional.of(userTest));
        Mockito.when(bankAccountDaoMock.findByIban(iban)).thenReturn(bankAccountTest);

        // THEN
        DataIncorrectException e = Assert.assertThrows(DataIncorrectException.class,()->transfertService.transfertBank(transfertBankTest) );
        assert (e.getMessage().contains("Transfert failed : Insufficient balance :"));

    }
}
