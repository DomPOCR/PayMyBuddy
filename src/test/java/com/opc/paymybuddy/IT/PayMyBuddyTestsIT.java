package com.opc.paymybuddy.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import com.opc.paymybuddy.dao.BankAccountDao;
import com.opc.paymybuddy.dao.RelationDao;
import com.opc.paymybuddy.dao.UserDao;
import com.opc.paymybuddy.model.BankAccount;
import com.opc.paymybuddy.model.User;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.math.BigDecimal;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PayMyBuddyTestsIT {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RelationDao relationDao;
	@Autowired
	private BankAccountDao bankAccountDao;

	@Test
	void addUser() throws Exception {

		String lastNameTest = "NomTest01";
		String firstNameTest = "PrenomTest01";
		String emailTest = "EmailTest01@email.com";
		String passwordTest = "pwd01";

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
				.andExpect(status().isCreated());

		User createdUser = userDao.findByEmail(emailTest);
		assertEquals(createdUser.getLastname(),lastNameTest);
	}
	@Test
	void addBuddy() throws Exception {

		String emailTest = "dp@email.com";

		// GIVEN
		ObjectMapper obm = new ObjectMapper();
		ObjectNode jsonBank = obm.createObjectNode();

		jsonBank.set("email", TextNode.valueOf(emailTest));

		long countRelation = relationDao.count();

		// WHEN
		// THEN
		mockMvc.perform(MockMvcRequestBuilders.post("/AddBuddy/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonBank.toString())
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());

		long newCountRelation = relationDao.count();
		assertEquals(newCountRelation,countRelation+1);
	}
	@Test
	void addBankAccount() throws Exception {

		String ibanTest = "IBAN001";
		String bicTest = "BIC001";
		String bankNameTest = "CREDIT AGRICOLE";
		String accountNameTest = "Compte professionnel";
		Integer userIdTest = 1;

		// GIVEN
		ObjectMapper obm = new ObjectMapper();
		ObjectNode jsonBank = obm.createObjectNode();

		jsonBank.set("iban", TextNode.valueOf(ibanTest));
		jsonBank.set("bic", TextNode.valueOf(bicTest));
		jsonBank.set("bankName", TextNode.valueOf(bankNameTest));
		jsonBank.set("accountName", TextNode.valueOf(accountNameTest));

		long countBankAccount = bankAccountDao.count();

		// WHEN
		// THEN
		mockMvc.perform(MockMvcRequestBuilders.post("/AddAccount/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonBank.toString())
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated());

		long newCountBankAccount = bankAccountDao.count();
		assertEquals(newCountBankAccount,countBankAccount+1);
	}
	@Test
	void addInternalTransfert() throws Exception {

		// Constantes pour le jeu de test
		Integer senderId = 1;
		Integer receiverId = 4;
		BigDecimal amount = BigDecimal.valueOf(15);
		String description = "Remboursement ciné";

		Optional<User> debitedUser = userDao.findById(senderId);
		Optional<User> creditedUser = userDao.findById(receiverId);

		BigDecimal debitedUserBalanceOld = debitedUser.get().getBalance();
		BigDecimal creditedUserBalanceOld = creditedUser.get().getBalance();

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
				.andExpect(status().isCreated());

		debitedUser = userDao.findById(senderId);
		creditedUser = userDao.findById(receiverId);

		BigDecimal debitedUserBalanceNew = debitedUser.get().getBalance();
		BigDecimal creditedUserBalanceNew = creditedUser.get().getBalance();

		assertEquals(debitedUserBalanceNew,debitedUserBalanceOld.subtract(amount));
		assertEquals(creditedUserBalanceNew,creditedUserBalanceOld.add(amount));
	}

	@Test
	void addExternalTransfertToTheBank() throws Exception {

		// Constantes pour le jeu de test
		Integer userId = 2;
		String iban = "IBANBAUER";
		BigDecimal amount = BigDecimal.valueOf(-50);
		String description = "Virement 50$ VERS ma banque";

		//Get parameters values
		ResourceBundle bundle = ResourceBundle.getBundle("application");
		double feeTx = Double.parseDouble(bundle.getString("application.fee.rate"));

		Optional<User> user = userDao.findById(userId);
		BigDecimal userBalanceOld = user.get().getBalance();

		BigDecimal fees = BigDecimal.valueOf(feeTx).multiply(amount);
		BigDecimal amountDebited = amount.add(fees);
		BigDecimal amountCredited = amount.abs().add(fees);

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

		user = userDao.findById(userId);
		BigDecimal userBalanceNew = user.get().getBalance();

		// Montant négatif pour un transfert vers la banque ==> add
		assertEquals(userBalanceNew.doubleValue(),userBalanceOld.add(amountDebited).doubleValue());

	}

	@Test
	void addExternalTransfertFromTheBank() throws Exception {

		// Constantes pour le jeu de test
		Integer userId = 2;
		String iban = "IBANBAUER";
		BigDecimal amount = BigDecimal.valueOf(500);
		String description = "Virement 500$ DEPUIS ma banque";

		//Get parameters values
		ResourceBundle bundle = ResourceBundle.getBundle("application");
		double feeTx = Double.parseDouble(bundle.getString("application.fee.rate"));

		Optional<User> user = userDao.findById(userId);
		BigDecimal userBalanceOld = user.get().getBalance();

		BigDecimal fees = BigDecimal.valueOf(feeTx).multiply(amount);

		BigDecimal amountCredited = amount.abs().subtract(fees);

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

		user = userDao.findById(userId);
		BigDecimal userBalanceNew = user.get().getBalance();

		assertEquals(userBalanceNew.doubleValue(),userBalanceOld.add(amountCredited).doubleValue());

	}
}
