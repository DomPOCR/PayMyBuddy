package com.opc.paymybuddy.UT_controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.dto.InternalTransfertDto;
import com.opc.paymybuddy.model.InternalTransfert;
import com.opc.paymybuddy.model.Transfert;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.service.TransfertService;
import com.opc.paymybuddy.service.UserService;
import com.opc.paymybuddy.web.controller.TransfertController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.util.DateUtil.now;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransfertController.class)
public class TransfertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransfertService transfertService;

    @MockBean
    private UserDao userDao;

    @MockBean
    private InternalTransfert internalTransfertMock;

    @MockBean
    private InternalTransfertDto internalTransfertDtoMock;

    // Constantes pour le jeu de test

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

    @BeforeEach
    public void setUpEach() {
        User userReceiver = new User(firstNameTest1, lastNameTest1, emailUserTest1, passwordTest1, balanceTest1, createDate1);
        User userSender = new User(firstNameTest2, lastNameTest2, emailUserTest2, passwordTest2, balanceTest2, createDate2);
    }

    @Test
    public void transfertBuddyControllerTest() throws Exception {

        // Constantes pour le jeu de test
        Integer senderId = 1;
        Integer receiverId = 4;
        BigDecimal amount = BigDecimal.valueOf(5);
        String description = "Remboursement café";

       //  TODO KO lorsque  le service est mocké
       // Mockito.when(transfertService.transfertBuddy(any(InternalTransfertDto.class))).thenReturn(internalTransfertDtoMock);

        // GIVEN
        ObjectMapper obm = new ObjectMapper();
        obm.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        ObjectNode jsonTransfert = obm.createObjectNode();

        jsonTransfert.set("senderId", IntNode.valueOf(senderId));
        jsonTransfert.set("receiverId",IntNode.valueOf(receiverId));
        jsonTransfert.set("amount",IntNode.valueOf(amount.intValue()));
        jsonTransfert.set("description", TextNode.valueOf(description));

        // WHEN
        // THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/transfert/buddy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTransfert.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
                //.andExpect(MockMvcResultMatchers.jsonPath("$..amount").value(amount));

    }
}
