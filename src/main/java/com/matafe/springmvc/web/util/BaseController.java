package com.matafe.springmvc.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.matafe.springmvc.core.config.SecurityUser;
import com.matafe.springmvc.core.security.User;

/**
 * Base Controller
 * 
 * @author Mauricio T. Ferraz
 */
public abstract class BaseController {

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	public static User getCurrentUser() {

		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof SecurityUser) {
			return ((SecurityUser) principal).getDomainUser();
		}

		return null;
	}

	public static boolean isLoggedIn() {
		return getCurrentUser() != null;
	}
}
