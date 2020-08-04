package com.opc.paymybuddy.UT_service;

import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.dto.UserDto;
import com.opc.paymybuddy.service.UserService;
import com.opc.paymybuddy.service.UserServiceImpl;
import com.opc.paymybuddy.web.exceptions.DataAlreadyExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.util.DateUtil.now;

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
    UserDao userDao;

    @Test
    public void addUserWithNoExistingEmailTest() throws Exception {

        // GIVEN

        // Constantes pour le jeu de test

        String lastNameTest = "NomTest01";
        String firstNameTest = "PrenomTest01";
        String emailTest = "EmailTest01@mail.com";
        String passwordTest = "pwd01";

        UserDto userDtoTest = new UserDto(emailTest, passwordTest, firstNameTest, lastNameTest);

        // WHEN
        Mockito.when(userDao.existsByEmail(emailTest)).thenReturn(Boolean.FALSE);

        // THEN
        Assertions.assertTrue(userServiceTest.addUser(userDtoTest));
        assert (userDtoTest.getLastName().equals(lastNameTest));
        assert (userDtoTest.getFirstName().equals(firstNameTest));
    }

    @Test
    public void addUserWithExistingEmailTest() throws Exception {

        // GIVEN

        // Constantes pour le jeu de test

        String lastNameTest = "NomTest01";
        String firstNameTest = "PrenomTest01";
        String emailTest = "EmailTest01@mail.com";
        String passwordTest = "pwd01";

        UserDto userDtoTest = new UserDto(emailTest, passwordTest, firstNameTest, lastNameTest);

        // WHEN
        Mockito.when(userDao.existsByEmail(emailTest)).thenReturn(Boolean.TRUE);

        // THEN
        try {
            Assertions.assertFalse(userServiceTest.addUser(userDtoTest));
        } catch (DataAlreadyExistException eExp) {
            assert (eExp.getMessage().contains("is already exist"));
        }
    }
}
