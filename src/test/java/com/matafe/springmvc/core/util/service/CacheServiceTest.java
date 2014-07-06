package com.matafe.springmvc.core.util.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.matafe.springmvc.core.config.AppConfig;

/**
 * Simple Spring Cache Test.
 * 
 * @author Mauricio T. Ferraz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class CacheServiceTest {

	@Autowired
	private SomeService someService;

	@Test
	public void tesFindOnACacheMethod() {
		// call a cached method
		Collection<String> messages = this.someService.findAllMessages();
		Assert.assertEquals(2, messages.size());
		Assert.assertEquals(1, this.someService.getCountFindAll());

		// call again 100 times a cached method
		for (int i = 0; i < 100; i++) {
			messages = this.someService.findAllMessages();
			Assert.assertEquals(2, messages.size());
			Assert.assertEquals(1, this.someService.getCountFindAll());
		}

		// call a method tha will evict the cache.
		this.someService.addMessage("addedMessage01");

		// call again the same cached method.
		messages = this.someService.findAllMessages();
		Assert.assertEquals(3, messages.size());
		Assert.assertEquals(2, this.someService.getCountFindAll());
	}
}

@Service
class SomeService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SomeService.class);

	private int countFindAll = 0;

	private AtomicInteger keyControl = new AtomicInteger(0);

	private Map<Integer, String> messages = new HashMap<>();

	@PostConstruct
	public void intiBean() {
		messages.put(keyControl.incrementAndGet(), "m1");
		messages.put(keyControl.incrementAndGet(), "m2");
	}

	@Cacheable(value = "test-message-cache")
	public Collection<String> findAllMessages() {
		LOGGER.debug("###findAllMessages called");
		countFindAll++;
		return this.messages.values();
	}

	@CacheEvict(value = "test-message-cache", allEntries = true)
	public void addMessage(String msg) {
		LOGGER.debug("###addMessage called with parameter = : " + msg);
		this.messages.put(keyControl.incrementAndGet(), msg);
	}

	public int getCountFindAll() {
		return this.countFindAll;
	}

}