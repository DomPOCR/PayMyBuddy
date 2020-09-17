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

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.util.DateUtil.now;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User userMock;


     // Encrypt password
    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    // Constantes pour le jeu de test

    String lastNameTest = "NomTest01";
    String firstNameTest = "PrenomTest01";
    String emailTest = "EmailTest01@email.com";
    String passwordTest = "pwd01";
    BigDecimal balanceTest = BigDecimal.valueOf(0);
    Date createDate = now();

    String existingLastNameTest = "P";
    String existingFirstNameTest = "Dom";
    String existingEmailTest = "dp@email.com";
    String existingPasswordTest = "mdp3";

    @BeforeEach
    public void setUpEach() {

        userMock = new User(lastNameTest,firstNameTest,emailTest,passwordTest,balanceTest,createDate);

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
    public void addUserControllerTest() throws Exception {

        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonUser = obm.createObjectNode();

        // GIVEN
        jsonUser.set("email", TextNode.valueOf(emailTest));
        jsonUser.set("password", TextNode.valueOf(passwordTest));
        jsonUser.set("firstname", TextNode.valueOf(firstNameTest));
        jsonUser.set("lastname", TextNode.valueOf(lastNameTest));

        // WHEN
        // THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/AddUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$..firstname").value(firstNameTest))
                .andExpect(MockMvcResultMatchers.jsonPath("$..lastname").value(lastNameTest))
                .andExpect(status().isCreated());
    }

    @Test
    public void connectUserControllerTest() throws Exception {

        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonUser = obm.createObjectNode();

        // GIVEN

        Mockito.when(userService.connectUser(any(UserDto.class))).thenReturn(true);

        jsonUser.set("email", TextNode.valueOf(existingEmailTest));
        jsonUser.set("password", TextNode.valueOf(encoder.encode(existingPasswordTest)));

        // WHEN
        // THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/Connect")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$..email").value(existingEmailTest))
                .andExpect(status().isOk());
    }


}
