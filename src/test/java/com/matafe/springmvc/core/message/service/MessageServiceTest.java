package com.matafe.springmvc.core.message.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.matafe.springmvc.core.config.AppConfig;
import com.matafe.springmvc.core.message.Message;
import com.matafe.springmvc.core.message.service.MessageQueryService;
import com.matafe.springmvc.core.message.service.MessageService;
import com.matafe.springmvc.core.security.User;

/**
 * Message Service Test.
 * 
 * @author Mauricio T. Ferraz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class MessageServiceTest {

	@Autowired
	private MessageService messageService;

	@Autowired
	private MessageQueryService messageQueryService;

	@Test
	public void testFindMessageByUser() {
		User user1 = new User(1L);
		List<Message> messages = messageQueryService.findMessagesByUser(user1
				.getId());
		Assert.assertNotNull(messages);
		Assert.assertEquals(10, messages.size());
	}

	@Test
	public void testFindAllMessages() {
		List<Message> messages = messageQueryService.findAllMessages();
		Assert.assertNotNull(messages);
		Assert.assertEquals(10 + 3, messages.size());
	}
}
