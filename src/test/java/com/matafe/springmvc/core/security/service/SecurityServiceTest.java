package com.matafe.springmvc.core.security.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.matafe.springmvc.core.config.AppConfig;
import com.matafe.springmvc.core.security.User;
import com.matafe.springmvc.core.security.service.SecurityQueryService;
import com.matafe.springmvc.core.security.service.SecurityService;

/**
 * Security Service Test.
 * 
 * @author Mauricio T. Ferraz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class SecurityServiceTest {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private SecurityQueryService securityQueryService;

	@Test
	public void testFindUserById() {
		User user = this.securityQueryService.findUserById(1L);
		System.out.println(user);
		Assert.assertNotNull(user);
	}

	@Test
	public void testFindAllusers() {
		List<User> users = this.securityQueryService.findAllUsers();
		Assert.assertNotNull(users);
		Assert.assertEquals(4, users.size());
		for (User user : users) {
			System.out.println(user);
		}
	}
}
