package com.matafe.springmvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matafe.springmvc.entities.Message;

/**
 * @author Mauricio T. Ferraz
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {

	List<Message> findByCreatedById(int userId);

}
