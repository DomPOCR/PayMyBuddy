package com.opc.paymybuddy.UT_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.opc.paymybuddy.dto.BankAccountDto;
import com.opc.paymybuddy.model.BankAccount;
import com.opc.paymybuddy.model.User;
import com.opc.paymybuddy.service.BankAccountService;
import com.opc.paymybuddy.web.controller.BankAccountController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankAccountController.class)
class BankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankAccountService bankAccountServiceMock;

    // Constantes pour le jeu de test

    String ibanTest = "IBAN001";
    String bicTest = "BIC001";
    String bankNameTest = "CREDIT AGRICOLE";
    String accountNameTest = "Compte professionnel";
    Integer userIdTest = 1;

    @Test
    public void listBankAccountTest() throws Exception {

        List<BankAccount> bankAccountList = new ArrayList<>();
        User userTest = new User();
        userTest.setId(userIdTest);

        // GIVEN
        bankAccountList.add( new BankAccount(ibanTest,bicTest,bankNameTest,accountNameTest, userTest));
        Mockito.when(bankAccountServiceMock.findAll()).thenReturn(bankAccountList);

        // WHEN
        // THEN

        mockMvc.perform(MockMvcRequestBuilders.get("/Accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$..iban").value(ibanTest))
                .andExpect(MockMvcResultMatchers.jsonPath("$..bankName").value(bankNameTest))
                .andExpect(status().isOk());
    }

    @Test
    void addBankAccountTest() throws Exception {

        List<BankAccount> bankAccountList = new ArrayList<>();

        User userTest = new User();

        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonBank = obm.createObjectNode();

        // GIVEN
        userTest.setId(userIdTest);
        bankAccountList.add( new BankAccount(ibanTest,bicTest,bankNameTest,accountNameTest, userTest));

        Mockito.when(bankAccountServiceMock.findAll()).thenReturn(bankAccountList);

        jsonBank.set("iban", TextNode.valueOf(ibanTest));
        jsonBank.set("bic", TextNode.valueOf(bicTest));
        jsonBank.set("bankName", TextNode.valueOf(bankNameTest));
        jsonBank.set("accountName", TextNode.valueOf(accountNameTest));

        // WHEN
        // THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/AddAccount/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBank.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
             //   .andExpect(MockMvcResultMatchers.jsonPath("$..iban").value(ibanTest))
             //  .andExpect(MockMvcResultMatchers.jsonPath("$..bankName").value(bankNameTest))
                .andExpect(status().isCreated());

    }
}