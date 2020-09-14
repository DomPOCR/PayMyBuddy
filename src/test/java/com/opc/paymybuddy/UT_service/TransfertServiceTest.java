package com.opc.paymybuddy.UT_service;

import com.opc.paymybuddy.dao.RelationDao;
import com.opc.paymybuddy.dao.TransfertDao;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.dto.InternalTransfertDto;
import com.opc.paymybuddy.model.Relation;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.service.TransfertService;
import com.opc.paymybuddy.service.TransfertServiceImpl;
import com.opc.paymybuddy.web.exceptions.DataIncorrectException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.util.DateUtil.now;
import static org.mockito.ArgumentMatchers.any;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
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
    UserDao userDaoMock;
    @MockBean
    RelationDao relationDaoMock;

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

    @Test // Cas passant //TODO KO sur le contains relation
    public void newInternalTransfertWithExistingBuddy() throws Exception {

        // GIVEN

        // Constantes pour le jeu de test
        Integer senderId = 1;
        Integer receiverId = 4;
        BigDecimal amount = BigDecimal.valueOf(5);
        String description = "Remboursement café";

        InternalTransfertDto internalTransfertTest = new InternalTransfertDto(senderId, receiverId, amount, description);

        User userReceiver = new User(firstNameTest1, lastNameTest1, emailUserTest1, passwordTest1, balanceTest1, createDate1);
        User userSender = new User(firstNameTest2, lastNameTest2, emailUserTest2, passwordTest2, balanceTest2, createDate2);

        Relation myBuddy = new Relation(userReceiver, userSender);
        List<Relation> listRelation = new ArrayList();
        listRelation.add(myBuddy);
        userSender.setListRelations(listRelation);
        relationDaoMock.save(myBuddy);

        // WHEN
        Mockito.when(userDaoMock.findById(any(Integer.class))).thenReturn(Optional.of(userReceiver));
        Mockito.when(userDaoMock.findById(any(Integer.class))).thenReturn(Optional.of(userSender));

        InternalTransfertDto internalTransfert = transfertService.transfertBuddy(internalTransfertTest);

        // THEN

        Assertions.assertNotNull(internalTransfert);

    }

    @Test // Cas non passant
    public void newInternalTransfertWithSameId() throws Exception {

        // GIVEN

        // Constantes pour le jeu de test
        Integer senderId = 1;
        Integer receiverId = 1;
        BigDecimal amount = BigDecimal.valueOf(5);
        String description = "Remboursement café";

        InternalTransfertDto internalTransfertTest = new InternalTransfertDto(senderId, receiverId, amount, description);

        User userReceiver = new User(firstNameTest1, lastNameTest1, emailUserTest1, passwordTest1, balanceTest1, createDate1);
        User userSender = new User(firstNameTest2, lastNameTest2, emailUserTest2, passwordTest2, balanceTest2, createDate2);

        Relation myBuddy = new Relation(userReceiver, userSender);
        List<Relation> listRelation = new ArrayList();
        listRelation.add(myBuddy);
        userSender.setListRelations(listRelation);
        relationDaoMock.save(myBuddy);

        // WHEN
        Mockito.when(userDaoMock.findById(any(Integer.class))).thenReturn(Optional.of(userReceiver));
        Mockito.when(userDaoMock.findById(any(Integer.class))).thenReturn(Optional.of(userSender));

        try {
            InternalTransfertDto internalTransfert = transfertService.transfertBuddy(internalTransfertTest);
        } catch (DataIncorrectException eExp) {
            assert (eExp.getMessage().contains("senderId and receiverId cannot be the same"));
        }

    }
}
