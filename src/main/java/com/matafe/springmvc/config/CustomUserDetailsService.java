package com.matafe.springmvc.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matafe.springmvc.entities.Role;
import com.matafe.springmvc.repositories.UserRepository;

/**
 * @author Mauricio T. Ferraz
 */
@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		try {
			com.matafe.springmvc.entities.User domainUser = userRepository
					.findByUserName(username);
			return new SecurityUser(domainUser);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static List<GrantedAuthority> getGrantedAuthorities(
			com.matafe.springmvc.entities.User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authorities;
	}
}
