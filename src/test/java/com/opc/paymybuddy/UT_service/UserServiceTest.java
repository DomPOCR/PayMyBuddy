package com.opc.paymybuddy.UT_service;

import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.dto.UserDto;
import com.opc.paymybuddy.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.util.DateUtil.now;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserServiceTest {

    @Autowired
    UserService userServiceTest;

    @MockBean
    UserDao userDao;

    // Constantes pour le jeu de test

    String lastNameTest = "NomTest01";
    String firstNameTest = "PrenomTest01";
    String emailTest = "EmailTest01";
    String passwordTest = "pwd01";
    BigDecimal balanceTest = BigDecimal.valueOf(1000.00);
    Date createDate = now();

    @Test
    public void addUserWithNoExistingEmailTest() throws Exception {

        // GIVEN

        UserDto userDtoTest = new UserDto(emailTest,passwordTest,lastNameTest,firstNameTest);

        // WHEN
        Mockito.when(userDao.existsByEmail(emailTest)).thenReturn(Boolean.FALSE);

        // THEN
        Assertions.assertTrue( userServiceTest.addUser(userDtoTest));
        assert (userDtoTest.getLastName().equals(lastNameTest));
        assert (userDtoTest.getFirstName().equals(firstNameTest));


    }

}
