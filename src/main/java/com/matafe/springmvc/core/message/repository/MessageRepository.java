package com.matafe.springmvc.core.message.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matafe.springmvc.core.message.Message;

/**
 * <p>
 * Message Repository.
 * 
 * @author Mauricio T. Ferraz
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findByCreatedById(Long userId);

}
