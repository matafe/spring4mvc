package com.matafe.springmvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.matafe.springmvc.entities.Message;
import com.matafe.springmvc.repositories.MessageRepository;
import com.matafe.springmvc.repositories.UserRepository;

/**
 * @author Mauricio T. Ferraz
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MessageQueryService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageRepository messageRepository;

	public List<Message> findAllMessages() {
		return messageRepository.findAll(new Sort(Sort.Direction.DESC,
				"createdBy"));
	}

	public List<Message> findAllMessages(int size) {
		return findAllMessages(1, size);
	}

	public List<Message> findAllMessages(int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(
				Sort.Direction.DESC, "createdBy"));
		return messageRepository.findAll(pageable).getContent();
	}

	public List<Message> findMessagesByUser(int userId) {
		return messageRepository.findByCreatedById(userId);
	}

}
