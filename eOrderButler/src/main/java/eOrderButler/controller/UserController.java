package eOrderButler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import eOrderButler.model.User;
import eOrderButler.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView getRegistrationForm() {
		User user = new User();
		//?
		return new ModelAndView("user");
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ModelAndView registerCustomer(@ModelAttribute(value = "user") User user,
			BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		if (result.hasErrors()) {
			modelAndView.setViewName("user");
			return modelAndView;
		}
		userService.addUser(user);
		modelAndView.setViewName("login");
		modelAndView.addObject("registrationSuccess", "Registered Successfully. Login using username and password");
		return modelAndView;
	}
}

