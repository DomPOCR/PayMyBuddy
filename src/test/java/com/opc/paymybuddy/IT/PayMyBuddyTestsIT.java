package com.opc.paymybuddy.IT;

import com.opc.paymybuddy.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PayMyBuddyTestsIT {
	@Autowired
	UserService userService;

	@Test
	void MyTest() {
		System.out.println(userService.count());

		Assertions.assertTrue(true);
	}

}
