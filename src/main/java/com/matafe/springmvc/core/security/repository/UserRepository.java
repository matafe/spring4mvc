package com.matafe.springmvc.core.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matafe.springmvc.core.security.User;

/**
 * <p>
 * User Repository.
 * 
 * @author Mauricio T. Ferraz
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	User findByUserName(String userName);

	User findByUserNameAndPassword(String userName, String password);

}
