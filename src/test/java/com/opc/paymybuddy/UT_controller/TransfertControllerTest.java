package com.opc.paymybuddy.UT_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.opc.paymybuddy.dto.ExternalTransfertDto;
import com.opc.paymybuddy.dto.InternalTransfertDto;
import com.opc.paymybuddy.service.TransfertService;
import com.opc.paymybuddy.web.controller.TransfertController;
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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransfertController.class)
public class TransfertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransfertService transfertService;

    @Test
    public void transfertBuddyControllerTest() throws Exception {

        // Constantes pour le jeu de test
        Integer senderId = 1;
        Integer receiverId = 4;
        BigDecimal amount = BigDecimal.valueOf(5);
        String description = "Remboursement café";

        InternalTransfertDto internalTransfertDto = new InternalTransfertDto(senderId,receiverId,BigDecimal.valueOf(amount.intValue()),description);
        Mockito.when(transfertService.transfertBuddy(any(InternalTransfertDto.class))).thenReturn(internalTransfertDto);

        // GIVEN
        ObjectMapper obm = new ObjectMapper();
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
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$..amount").value(amount.intValue()));

    }
    @Test
    public void TransfertToTheBankTest() throws Exception{

        // Constantes pour le jeu de test
        Integer userId = 2;
        String iban = "IBANBAUER";
        BigDecimal amount = BigDecimal.valueOf(-500);
        String description = "Virement VERS ma banque";

        ExternalTransfertDto externalTransfertDto = new ExternalTransfertDto(userId,iban,amount);
        Mockito.when(transfertService.transfertBank(any(ExternalTransfertDto.class))).thenReturn(externalTransfertDto);

        // GIVEN
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonTransfert = obm.createObjectNode();

        jsonTransfert.set("userId", IntNode.valueOf(userId));
        jsonTransfert.set("iban",TextNode.valueOf(iban));
        jsonTransfert.set("amount",IntNode.valueOf(amount.intValue()));
        jsonTransfert.set("description", TextNode.valueOf(description));

        // WHEN
        // THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/transfert/bank")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTransfert.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void TransfertFromTheBankTest() throws Exception{

        // Constantes pour le jeu de test
        Integer userId = 2;
        String iban = "IBANBAUER";
        BigDecimal amount = BigDecimal.valueOf(500);
        String description = "Virement DEPUIS ma banque";

        ExternalTransfertDto externalTransfertDto = new ExternalTransfertDto(userId,iban,amount);
        Mockito.when(transfertService.transfertBank(any(ExternalTransfertDto.class))).thenReturn(externalTransfertDto);

        // GIVEN
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonTransfert = obm.createObjectNode();

        jsonTransfert.set("userId", IntNode.valueOf(userId));
        jsonTransfert.set("iban",TextNode.valueOf(iban));
        jsonTransfert.set("amount",IntNode.valueOf(amount.intValue()));
        jsonTransfert.set("description", TextNode.valueOf(description));

        // WHEN
        // THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/transfert/bank")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTransfert.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void TransfertFromTheBankTestMissingUserId() throws Exception{

        // Constantes pour le jeu de test
        Integer userId = 2;
        String iban = "IBANBAUER";
        BigDecimal amount = BigDecimal.valueOf(500);
        String description = "Virement DEPUIS ma banque";

        ExternalTransfertDto externalTransfertDto = new ExternalTransfertDto(userId,iban,amount);
        Mockito.when(transfertService.transfertBank(any(ExternalTransfertDto.class))).thenReturn(externalTransfertDto);

        // GIVEN
        ObjectMapper obm = new ObjectMapper();
        ObjectNode jsonTransfert = obm.createObjectNode();


        jsonTransfert.set("iban",TextNode.valueOf(iban));
        jsonTransfert.set("amount",IntNode.valueOf(amount.intValue()));
        jsonTransfert.set("description", TextNode.valueOf(description));

        // WHEN
        // THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/transfert/bank")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTransfert.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
