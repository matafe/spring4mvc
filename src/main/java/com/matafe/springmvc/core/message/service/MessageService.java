package com.matafe.springmvc.core.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matafe.springmvc.core.message.Message;
import com.matafe.springmvc.core.message.repository.MessageRepository;
import com.matafe.springmvc.core.util.AbstractService;

/**
 * <p>
 * Message Service.
 * 
 * @author Mauricio T. Ferraz
 */
@Service
public class MessageService extends AbstractService {

	@Autowired
	private MessageRepository messageRepository;

	public void createMessage(Message msg) {
		this.messageRepository.save(msg);
	}

}
