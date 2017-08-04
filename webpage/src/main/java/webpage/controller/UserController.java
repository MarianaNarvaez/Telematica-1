package webpage.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webpage.views.Login;
import webpage.views.entities.User;

@Controller
public class UserController {

	@Autowired
	private Login login;
	@Autowired
	private NotificationService notifyService;

	@RequestMapping("/login")
	public String loginPage(User user) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			notifyService.addErrorMessage("Please fill the form correctly!");
			return "/login";
		}
		long userCod=-1;
		if ((userCod=login.authenticate(user.getUsername(), user.getPassword()))==-1) {
			notifyService.addErrorMessage("Invalid Username or Password!");
			return "/login";
		}
		model.addAttribute("loggedUser",userCod);
		notifyService.addInfoMessage("Login successful");
		return "redirect:/";
	}

	@RequestMapping("/register")
	public String registerPage(User user, Model model) {
		return "/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			notifyService.addErrorMessage("Please fill the form correctly!");
			return "/register";
		}
		long save;
		if ((save=login.save(user))!=0) {
			if(save==-2)notifyService.addErrorMessage("User already exist!");
			if(save==-1)notifyService.addErrorMessage("Unknow Error :c");
			return "/register";
		}

		notifyService.addInfoMessage("Sing up successful");
		return "redirect:/login";
	}
}
