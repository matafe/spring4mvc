package com.matafe.springmvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matafe.springmvc.entities.Role;
import com.matafe.springmvc.entities.User;
import com.matafe.springmvc.repositories.RoleRepository;
import com.matafe.springmvc.repositories.UserRepository;

/**
 * @author Mauricio T. Ferraz
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public void createUser(User user) {
		if (checkUserNameExists(user.getUserName())) {
			throw new RuntimeException("UserName [" + user.getUserName()
					+ "] already exist");
		}
		if (checkEmailExists(user.getEmail())) {
			throw new RuntimeException("Email [" + user.getEmail()
					+ "] already exist");
		}
		userRepository.save(user);
	}

	public User findUserById(Integer userId) {
		return userRepository.findOne(userId);
	}

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	public boolean checkEmailExists(String email) {
		return userRepository.findByEmail(email) != null;
	}

	public boolean checkUserNameExists(String userName) {
		return userRepository.findByUserName(userName) != null;
	}

	public User login(String userName, String password) {
		return userRepository.findByUserNameAndPassword(userName, password);
	}

	public void addRole(Role role) {
		roleRepository.save(role);
	}
}
