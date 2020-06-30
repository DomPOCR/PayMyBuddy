package com.ocr.paymybuddy.UT_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.util.DateUtil.now;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@ContextConfiguration
//@SpringBootApplication
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Constantes pour le jeu de test

    String lastNameTest = "NomTest01";
    String firstNameTest = "PrenomTest01";
    String emailTest = "EmailTest01";
    String passwordTest = "pwd01";
    BigDecimal balanceTest = BigDecimal.valueOf(1000.00);
    Date createDate = now();
    boolean isActive = true;

    //@Test
      public void getAllUserControllerTest() throws Exception {

        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonUser = obm.createObjectNode();

        // GIVEN

        jsonUser.set("firstName", TextNode.valueOf(firstNameTest));
        jsonUser.set("lastName", TextNode.valueOf(lastNameTest));
        jsonUser.set("emailTest", TextNode.valueOf(emailTest));
        jsonUser.set("passwordTest", TextNode.valueOf(passwordTest));
        jsonUser.set("balanceTest", TextNode.valueOf(String.valueOf(balanceTest)));
        jsonUser.set("isActive", TextNode.valueOf(String.valueOf(isActive)));


        // WHEN
        // THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/Users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
