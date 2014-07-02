
package com.matafe.springmvc.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.matafe.springmvc.entities.Message;
import com.matafe.springmvc.entities.Role;
import com.matafe.springmvc.entities.User;
import com.matafe.springmvc.services.MessageService;
import com.matafe.springmvc.services.UserService;

/**
 * @author Mauricio T. Ferraz
 */
@Component
public class DBInitializer
{
	private static Logger logger = LoggerFactory.getLogger(DBInitializer.class);

	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;

	@Value("${init-db:false}")
	private String initDatabase;

	@PostConstruct
	public void init()
	{
		try {
			logger.info("############## InitDatabase :" + initDatabase + " ########################");
			if (Boolean.parseBoolean(initDatabase)) {
				initDatabase();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void initDatabase()
	{
		logger.info("Initializing Database with sample data");
		
		Role roleUser = new Role("ROLE_USER");
		Role roleAdmin = new Role("ROLE_ADMIN");
		
		User admin = new User(1, "admin", "admin", "Administrator", "admin@gmail.com");
		User user1 = new User(2, "user1", "user1", "Usuario 1", "user1@gmail.com");
		
		admin.addRoles(roleUser, roleAdmin);
		user1.addRoles(roleUser);
		
		userService.createUser(admin);
		userService.createUser(user1);
		
		Message m1 = new Message(0, "Message 1", admin);
		Message m2 = new Message(0, "Message 2", admin);
		Message m3 = new Message(0, "Message 3", user1);

		messageService.createMessage(m1);
		messageService.createMessage(m2);
		messageService.createMessage(m3);

	}
}
