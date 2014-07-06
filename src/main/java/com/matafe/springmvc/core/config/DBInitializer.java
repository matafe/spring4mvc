package com.matafe.springmvc.core.config;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.matafe.springmvc.core.message.Message;
import com.matafe.springmvc.core.message.service.MessageService;
import com.matafe.springmvc.core.security.Role;
import com.matafe.springmvc.core.security.User;
import com.matafe.springmvc.core.security.service.SecurityService;

/**
 * @author Mauricio T. Ferraz
 */
@Component
public class DBInitializer {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DBInitializer.class);

	@Autowired
	private SecurityService userService;

	@Autowired
	private MessageService messageService;

	@Value("${init-db:false}")
	private String initDatabase;

	@PostConstruct
	public void init() {
		try {
			if (Boolean.parseBoolean(initDatabase)) {
				LOGGER.info("############## InitDatabase :" + initDatabase
						+ " ########################");
				initDatabase();
			}
		} catch (Exception e) {
			LOGGER.error(
					"Could not initialize the database with the sample data", e);
		}
	}

	void initDatabase() {
		LOGGER.info("Initializing Database with sample data");

		Role roleUser = new Role("ROLE_USER");
		Role roleAdmin = new Role("ROLE_ADMIN");

		User admin1 = new User(1L, "admin1", "admin1", "Administrator 1",
				"admin1@gmail.com");
		User admin2 = new User(2L, "admin2", "admin2", "Administrator 2",
				"admin2@gmail.com");
		User user1 = new User(3L, "user1", "user1", "Usuario 1",
				"user1@gmail.com");
		User user2 = new User(4L, "user2", "user2", "Usuario 2",
				"user2@gmail.com");

		List<User> users = Arrays.asList(new User[] { admin1, admin2, user1,
				user2 });

		// add the roles to the users
		admin1.addRoles(roleUser, roleAdmin);
		admin2.addRoles(roleUser, roleAdmin);
		user1.addRoles(roleUser);
		user2.addRoles(roleUser);

		// create the users
		for (User user : users) {
			userService.createUser(user);
		}

		// create the messages for the admin1 user
		for (int i = 1; i <= 10; i++) {
			Message message = new Message(0L, "Message " + i, admin1);
			messageService.createMessage(message);
		}

		// create the messages for the user1 user
		for (int i = 1; i <= 3; i++) {
			Message message = new Message(0L, "Message " + i, user1);
			messageService.createMessage(message);
		}

	}
}
