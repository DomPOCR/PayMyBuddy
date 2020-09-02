package com.opc.paymybuddy.UT_service;

import com.opc.paymybuddy.dao.RelationDao;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.dto.UserDto;
import com.opc.paymybuddy.model.Relation;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.service.UserService;
import com.opc.paymybuddy.service.UserServiceImpl;
import com.opc.paymybuddy.web.exceptions.DataAlreadyExistException;
import com.opc.paymybuddy.web.exceptions.DataMissingException;
import com.opc.paymybuddy.web.exceptions.DataNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.util.DateUtil.now;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceTestsContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    UserService userServiceTest;

    @MockBean
    UserDao userDaoMock;
    @MockBean
    RelationDao relationDaoMock;

    // Encrypt password
    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    /* ---------------------------------------------------------------------------- */
    /*                                AddUser                                       */
    /* ---------------------------------------------------------------------------- */

    @Test // Cas passant
    public void addUserWithNoExistingEmailTest() throws Exception {

        // GIVEN

        // Constantes pour le jeu de test

        String lastNameTest = "NomTest01";
        String firstNameTest = "PrenomTest01";
        String emailTest = "EmailTest01@mail.com";
        String passwordTest = "pwd01";

        UserDto userDtoTest = new UserDto(emailTest, passwordTest, firstNameTest, lastNameTest);

        // WHEN
        Mockito.when(userDaoMock.existsByEmail(emailTest)).thenReturn(Boolean.FALSE);

        // THEN
        Assertions.assertTrue(userServiceTest.addUser(userDtoTest));

        assert (userDtoTest.getLastname().equals(lastNameTest));
        assert (userDtoTest.getFirstname().equals(firstNameTest));
    }

    @Test // Cas non passant
    public void addUserWithExistingEmailTest() throws Exception {

        // GIVEN

        // Constantes pour le jeu de test

        String lastNameTest = "NomTest01";
        String firstNameTest = "PrenomTest01";
        String emailTest = "EmailTest01@mail.com";
        String passwordTest = "pwd01";

        UserDto userDtoTest = new UserDto(emailTest, passwordTest, firstNameTest, lastNameTest);

        // WHEN
        Mockito.when(userDaoMock.existsByEmail(emailTest)).thenReturn(Boolean.TRUE);

        // THEN
        try {
            Assertions.assertFalse(userServiceTest.addUser(userDtoTest));
        } catch (DataAlreadyExistException eExp) {
            assert (eExp.getMessage().contains("is already exist"));
        }
    }

    /* ---------------------------------------------------------------------------- */
    /*                                ConnectUser                                   */
    /* ---------------------------------------------------------------------------- */

    @Test // Cas passant
    public void connectUserWithExistingEmail() throws Exception {

        // GIVEN

        // Constantes pour le jeu de test

        String lastNameTest = "Bauer";
        String firstNameTest = "Jack";
        String emailTest = "jb@email.com";
        // String passwordTest = encoder.encode("mpd2");
        String passwordTest = "mpd2";
        BigDecimal balanceTest = BigDecimal.valueOf(0);
        Date createDate = now();

        UserDto userDtoTest = new UserDto(emailTest, passwordTest, firstNameTest, lastNameTest);
        User userRegistered = new User(firstNameTest, lastNameTest, emailTest, encoder.encode(passwordTest), balanceTest, createDate);

        // WHEN

        Mockito.when(userDaoMock.findByEmail(any(String.class))).thenReturn(userRegistered);
        Mockito.when(userDaoMock.save(any(User.class))).thenReturn(userRegistered);

        // THEN
        Assertions.assertTrue(userServiceTest.connectUser(userDtoTest));

    }

    @Test // Cas non passant
    public void connectUserWithEmptyEmail() throws Exception {

        // GIVEN

        // Constantes pour le jeu de test

        String lastNameTest = "NomTest01";
        String firstNameTest = "PrenomTest01";
        String emailTest = "";
        String passwordTest = "pwd01";

        UserDto userDtoTest = new UserDto(emailTest, passwordTest, firstNameTest, lastNameTest);

        // WHEN
        // THEN
        try {
            userServiceTest.connectUser(userDtoTest);
        } catch (
                DataMissingException eExp) {
            assert (eExp.getMessage().contains("email is required"));
        }
    }

    @Test // Cas non passant
    public void connectUserWithEmptyPassword() throws Exception {

        // GIVEN

        // Constantes pour le jeu de test

        String lastNameTest = "NomTest01";
        String firstNameTest = "PrenomTest01";
        String emailTest = "EmailTest01@mail.com";
        String passwordTest = "";

        UserDto userDtoTest = new UserDto(emailTest, passwordTest, firstNameTest, lastNameTest);

        // WHEN
        // THEN
        try {
            userServiceTest.connectUser(userDtoTest);
        } catch (
                DataMissingException eExp) {
            assert (eExp.getMessage().contains("password is required"));
        }
    }

    /* ---------------------------------------------------------------------------- */
    /*                                AddBuddy                                      */
    /* ---------------------------------------------------------------------------- */

    @Test // Cas passant
    public void addBuddyToExistingUserTest() throws Exception {

        // GIVEN

        // Constantes pour le jeu de test
        String lastNameTest = "NomTest01";
        String firstNameTest = "PrenomTest01";
        String emailUserTest = "jb@email.com";
        String emailBuddyTest = "Test@email.com";
        String passwordTest = "pwd01";
        BigDecimal balanceTest = BigDecimal.valueOf(0);
        Date createDate = now();


        // WHEN
        User buddyToAdd = new User(firstNameTest, lastNameTest, emailBuddyTest, passwordTest, balanceTest, createDate);

        User userTest = new User(firstNameTest, lastNameTest, emailUserTest, passwordTest, balanceTest, createDate);
        List<Relation> relationList = new ArrayList<>();

        Relation userToAddRelation = new Relation(userTest, buddyToAdd);

        userTest.setListRelations(relationList);

        Mockito.when(userDaoMock.findByEmail((any(String.class)))).thenReturn(buddyToAdd);
        Mockito.when(userDaoMock.findById(any(Integer.class))).thenReturn(Optional.of(userTest));

        Mockito.when(userDaoMock.save(any(User.class))).thenReturn(buddyToAdd);
        Mockito.when(relationDaoMock.save(any(Relation.class))).thenReturn(userToAddRelation);

        User userToUpdate = userServiceTest.addBuddy(buddyToAdd.getEmail(), 1);

        // THEN

        Assertions.assertNotNull(userToUpdate);

    }

    @Test // Cas non passant
    public void addBuddyToNonExistingUserTest() throws Exception {

        // GIVEN

        // Constantes pour le jeu de test
        String lastNameTest = "NomTest01";
        String firstNameTest = "PrenomTest01";
        String emailTest = "jb@email.com";
        String passwordTest = "pwd01";
        BigDecimal balanceTest = BigDecimal.valueOf(0);
        Date createDate = now();


        // WHEN
        User buddyToAdd = new User(firstNameTest, lastNameTest, emailTest, passwordTest, balanceTest, createDate);
        User userTest = new User(firstNameTest, lastNameTest, emailTest, passwordTest, balanceTest, createDate);

        Mockito.when(userDaoMock.findByEmail((any(String.class)))).thenReturn(buddyToAdd);
        Mockito.when(userDaoMock.findById(any(Integer.class))).thenReturn(Optional.empty());
        Mockito.when(userDaoMock.save(any(User.class))).thenReturn(buddyToAdd);


        // THEN
        try {
            userServiceTest.addBuddy(buddyToAdd.getEmail(), 9999);
        } catch (
                DataNotExistException eExp) {
            assert (eExp.getMessage().contains("user 9999 does not exist"));
        }
    }

    @Test // Cas non passant
    public void addBuddyWithEmptyEmailToExistingUserTest() throws Exception {

        // GIVEN

        // Constantes pour le jeu de test
        String lastNameTest = "NomTest01";
        String firstNameTest = "PrenomTest01";
        String emailTest = "";
        String passwordTest = "pwd01";
        BigDecimal balanceTest = BigDecimal.valueOf(0);
        Date createDate = now();


        // WHEN
        User userTest = new User(firstNameTest, lastNameTest, emailTest, passwordTest, balanceTest, createDate);

        // THEN
        try {
            userServiceTest.addBuddy(userTest.getEmail(), 1);
        } catch (
                DataMissingException eExp) {
            assert (eExp.getMessage().contains("email is required"));
        }
    }

    @Test // Cas non passant
    public void addBuddyWithSameEmailToExistingUserTest() throws Exception {

        // GIVEN

        // Constantes pour le jeu de test
        String lastNameTest = "NomTest01";
        String firstNameTest = "PrenomTest01";
        String emailTest = "jb@email.com";
        String passwordTest = "pwd01";
        BigDecimal balanceTest = BigDecimal.valueOf(0);
        Date createDate = now();


        // WHEN
        User buddyToAdd = new User(firstNameTest, lastNameTest, emailTest, passwordTest, balanceTest, createDate);
        User userTest = new User(firstNameTest, lastNameTest, emailTest, passwordTest, balanceTest, createDate);
        List<Relation> relationList = new ArrayList<>();

        Relation userToAddRelation = new Relation(userTest, buddyToAdd);
        relationList.add(userToAddRelation);

        userTest.setListRelations(relationList);

        Mockito.when(userDaoMock.findByEmail((any(String.class)))).thenReturn(buddyToAdd);
        Mockito.when(userDaoMock.findById(any(Integer.class))).thenReturn(Optional.of(userTest));

        // THEN
        try {
            userServiceTest.addBuddy(userTest.getEmail(), 1);
        } catch (
                DataAlreadyExistException eExp) {
            assert (eExp.getMessage().contains("has the same mail as the user to update"));
        }
    }

    @Test // Cas non passant
    public void addBuddyWithExistingEmailToExistingUserTest() throws Exception {

        // GIVEN

        // Constantes pour le jeu de test
        String lastNameTest = "NomTest01";
        String firstNameTest = "PrenomTest01";
        String emailBuddyTest = "jb@email.com";
        String emailUserTest = "jb1@email.com";
        String passwordTest = "pwd01";
        BigDecimal balanceTest = BigDecimal.valueOf(0);
        Date createDate = now();


        // WHEN
        User buddyToAdd = new User(firstNameTest, lastNameTest, emailBuddyTest, passwordTest, balanceTest, createDate);
        User userTest = new User(firstNameTest, lastNameTest, emailUserTest, passwordTest, balanceTest, createDate);
        List<Relation> relationList = new ArrayList<>();

        Relation userToAddRelation = new Relation(userTest, buddyToAdd);
        relationList.add(userToAddRelation);

        userTest.setListRelations(relationList);

        Mockito.when(userDaoMock.findByEmail((any(String.class)))).thenReturn(buddyToAdd);
        Mockito.when(userDaoMock.findById(any(Integer.class))).thenReturn(Optional.of(userTest));

        // THEN
        try {
            userServiceTest.addBuddy(userTest.getEmail(), 1);
        } catch (
                DataAlreadyExistException eExp) {
            assert (eExp.getMessage().contains("already exist for this user"));
        }
    }
}
