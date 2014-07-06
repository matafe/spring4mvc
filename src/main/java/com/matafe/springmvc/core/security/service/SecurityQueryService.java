package com.matafe.springmvc.core.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.matafe.springmvc.core.security.User;
import com.matafe.springmvc.core.security.repository.RoleRepository;
import com.matafe.springmvc.core.security.repository.UserRepository;
import com.matafe.springmvc.core.util.AbstractQueryService;

/**
 * <p>
 * Security Query Service.
 * 
 * @author Mauricio T. Ferraz
 */
@Service
public class SecurityQueryService extends AbstractQueryService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Cacheable(value = "users")
	public User findUserById(Long id) {
		return this.userRepository.findOne(id);
	}

	@Cacheable("users")
	public List<User> findAllUsers() {
		return this.userRepository.findAll();
	}

	@Cacheable(value = "users")
	public boolean checkEmailExists(String email) {
		return this.userRepository.findByEmail(email) != null;
	}

	@Cacheable(value = "users")
	public boolean checkUserNameExists(String userName) {
		return this.userRepository.findByUserName(userName) != null;
	}

	public User login(String userName, String password) {
		return this.userRepository
				.findByUserNameAndPassword(userName, password);
	}
}
