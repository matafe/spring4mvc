package com.matafe.springmvc.core.message.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.matafe.springmvc.core.message.Message;
import com.matafe.springmvc.core.message.repository.MessageRepository;
import com.matafe.springmvc.core.security.repository.UserRepository;
import com.matafe.springmvc.core.util.AbstractQueryService;

/**
 * <p>
 * Message Query Service.
 * 
 * @author Mauricio T. Ferraz
 */
@Service
public class MessageQueryService extends AbstractQueryService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageRepository messageRepository;

	public List<Message> findAllMessages() {
		return this.messageRepository.findAll(new Sort(Sort.Direction.DESC,
				"createdBy"));
	}

	public List<Message> findAllMessages(int size) {
		return findAllMessages(1, size);
	}

	public List<Message> findAllMessages(int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(
				Sort.Direction.DESC, "createdBy"));
		return this.messageRepository.findAll(pageable).getContent();
	}

	public List<Message> findMessagesByUser(Long userId) {
		return this.messageRepository.findByCreatedById(userId);
	}

}
