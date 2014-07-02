package com.matafe.springmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matafe.springmvc.entities.User;

/**
 * @author Mauricio T. Ferraz
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

	User findByUserName(String userName);

	User findByUserNameAndPassword(String userName, String password);

}
