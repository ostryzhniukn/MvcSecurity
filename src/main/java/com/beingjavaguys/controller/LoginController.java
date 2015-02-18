package com.beingjavaguys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.beingjavaguys.dao.RegisterDao;
import com.beingjavaguys.domain.Login;
import com.beingjavaguys.hash.HashCode;

@Controller
public class LoginController {

	@Autowired
	RegisterDao registerDao;

	@Autowired
	HashCode hashCode;
	
//	@RequestMapping("registerform")
//	public ModelAndView registrationForm(@ModelAttribute Login login) {
//		return new ModelAndView("register", "login", login);
//	}

    @RequestMapping("registerform")
    public String registrationForm() {
        return "register";
    }

	@RequestMapping("register")
	public ModelAndView registerUser(@ModelAttribute Login login) {
		String hashPassword = hashCode.getHashPassword(login.getPassword());
		login.setPassword(hashPassword);
		registerDao.registerUser(login);
		return new ModelAndView("login", "login", login);
	}

	@RequestMapping("login")
	public ModelAndView getLoginForm(
			@RequestParam(required = false) String authfailed, String logout,
			String denied) {
		String message = "";
		if (authfailed != null) {
			message = "Invalid username of password, try again !";
		} else if (logout != null) {
			message = "Logged Out successfully, login again to continue !";
		} else if (denied != null) {
			message = "Access denied for this user !";
		}
		return new ModelAndView("login", "message", message);
	}

	@RequestMapping("user")
	public String geUserPage() {
		return "user";
	}

	@RequestMapping("admin")
	public String geAdminPage() {
		return "admin";
	}

	@RequestMapping("403page")
	public String ge403denied() {
		return "redirect:login?denied";
	}

}
