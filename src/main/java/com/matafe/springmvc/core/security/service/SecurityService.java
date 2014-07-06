package com.matafe.springmvc.core.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matafe.springmvc.core.security.Role;
import com.matafe.springmvc.core.security.User;
import com.matafe.springmvc.core.security.repository.RoleRepository;
import com.matafe.springmvc.core.security.repository.UserRepository;

/**
 * Security Service.
 * 
 * @author Mauricio T. Ferraz
 */
@Service
@Transactional
public class SecurityService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private SecurityQueryService securityQueryService;

	@CacheEvict(value = "users", allEntries = true)
	public void createUser(User user) {
		if (this.securityQueryService.checkUserNameExists(user.getUserName())) {
			throw new RuntimeException("UserName [" + user.getUserName()
					+ "] already exist");
		}
		if (this.securityQueryService.checkEmailExists(user.getEmail())) {
			throw new RuntimeException("Email [" + user.getEmail()
					+ "] already exist");
		}
		userRepository.save(user);
	}

	public void addRole(Role role) {
		roleRepository.save(role);
	}
}
