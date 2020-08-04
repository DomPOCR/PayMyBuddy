package com.opc.paymybuddy.UT_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.opc.paymybuddy.dto.UserDto;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.service.UserService;
import com.opc.paymybuddy.web.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.util.DateUtil.now;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private User userMock;

    @MockBean
    private UserDto userDtoMock;


    // Constantes pour le jeu de test

    String lastNameTest = "NomTest01";
    String firstNameTest = "PrenomTest01";
    String emailTest = "EmailTest01@email.com";
    String passwordTest = "pwd01";
    BigDecimal balanceTest = BigDecimal.valueOf(0);
    Date createDate = now();

    @BeforeEach
    public void setUpEach() {

        userMock = new User();
        userMock.setLastname(lastNameTest);
        userMock.setFirstname(firstNameTest);
        userMock.setEmail(emailTest);
        userMock.setPassword(passwordTest);
        userMock.setBalance(balanceTest);
        userMock.setCreateDate(createDate);

        userDtoMock = new UserDto(emailTest,passwordTest,lastNameTest,firstNameTest);

    }

    @Test
    public void getAllUserControllerTest() throws Exception {

        List<User> userList = new ArrayList<>();

        // GIVEN
        userList.add(userMock);
        Mockito.when(userService.findAll()).thenReturn(userList);

        // WHEN
        // THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/Users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$..firstname").value(firstNameTest))
                .andExpect(MockMvcResultMatchers.jsonPath("$..lastname").value(lastNameTest))
                .andExpect(status().isOk());
    }

    @Test
    public void addUserWithNonExistingEmailTest() throws Exception {

        List<UserDto> userList = new ArrayList<>();

        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonUser = obm.createObjectNode();

        // GIVEN
        userList.add(userDtoMock);
        Mockito.when(userService.addUser(userDtoMock)).thenReturn(true);

        jsonUser.set("email", TextNode.valueOf(emailTest));
        jsonUser.set("password", TextNode.valueOf(passwordTest));
        jsonUser.set("firstName", TextNode.valueOf(firstNameTest));
        jsonUser.set("lastName", TextNode.valueOf(lastNameTest));

        // WHEN
        // THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/AddUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$..firstName").value(firstNameTest))
                .andExpect(MockMvcResultMatchers.jsonPath("$..lastName").value(lastNameTest))
                .andExpect(status().isCreated());
    }


}
