package com.victor.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.victor.domain.User;

@ContextConfiguration("classpath:/victor-context.xml")
public class UserServiceTest extends AbstractTestNGSpringContextTests{

	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Test
	public void hasMatchUser() {
		boolean b1 = userService.hasMatchUser("admin", "123456");
		boolean b2 = userService.hasMatchUser("admin", "123qwe");
		
		assertTrue(b1);
		assertTrue(!b2);
	}
	
	@Test
	public void findUserByUserName() {
		User user = userService.findUserByUserName("admin");
		assertEquals(user.getUserName(), "admin");
	}
	
	@Test
	public void loginSuccess() {
		User user = userService.findUserByUserName("admin");
		user.setLastIp("192.168.63.151");
		user.setLastVisit(new Date());
		userService.loginSuccess(user);
	}
}
