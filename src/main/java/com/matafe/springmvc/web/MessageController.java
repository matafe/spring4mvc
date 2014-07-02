package com.matafe.springmvc.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.matafe.springmvc.entities.Message;
import com.matafe.springmvc.services.EmailService;
import com.matafe.springmvc.services.MessageQueryService;
import com.matafe.springmvc.services.MessageService;
import com.matafe.springmvc.services.UserService;
import com.matafe.springmvc.web.model.AjaxResponse;
import com.matafe.springmvc.web.model.ContactForm;

/**
 * @author Mauricio T. Ferraz
 */
@Controller
public class MessageController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private MessageQueryService messageQueryService;

	@Autowired
	private EmailService emailService;

	@RequestMapping("/welcome")
	public String welcome(Model model) {
		model.addAttribute("messages", messageQueryService.findAllMessages());
		return "welcome";
	}

	@RequestMapping(value = "/messages", method = RequestMethod.POST)
	public String saveMessage(Message msg, HttpSession session) {
		try {
			msg.setCreatedBy(getCurrentUser());
			messageService.createMessage(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:welcome";
	}

	@RequestMapping(value = "/messages", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Message> getMessages() {
		List<Message> messages = messageQueryService.findAllMessages();
		return messages;
	}

	@RequestMapping(value = "/contactUs", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResponse contactUs(
			@RequestBody @Validated ContactForm contactForm,
			BindingResult result) {
		AjaxResponse response = new AjaxResponse();
		if (result.hasErrors()) {
			response.setStatus(false);
			String errors = "";
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				// response.addError(fieldError.getDefaultMessage());
				errors += fieldError.getDefaultMessage() + "\n";
			}
			response.setMessage(errors);
			return response;
		}
		// System.out.println(contactForm);
		try {
			emailService.sendMail(contactForm.getEmail(),
					contactForm.getTitle(), contactForm.getMessage());
		} catch (Exception e) {
			response.setStatus(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
}
