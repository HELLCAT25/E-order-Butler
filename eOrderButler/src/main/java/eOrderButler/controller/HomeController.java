package eOrderButler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		
		System.out.println("error: " + error);
		System.out.println("logout: " + logout);
		
		if (error != null) {
			modelAndView.addObject("error", "Invalid username and Password");
		} else if (error == null) {
			System.out.println("error is null");
		}
		
		if (logout != null) {
			modelAndView.addObject("logout", "You have logged out successfully");
		}
		return modelAndView;
	}
}
