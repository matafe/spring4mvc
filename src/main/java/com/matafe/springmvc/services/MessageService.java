package com.matafe.springmvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matafe.springmvc.entities.Message;
import com.matafe.springmvc.repositories.MessageRepository;

/**
 * @author Mauricio T. Ferraz
 */
@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public void createMessage(Message msg) {
		messageRepository.save(msg);
	}

}
