package webpage.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webpage.views.UsersView;
import webpage.views.entities.User;

@Controller
public class UserController {

	@Autowired
	private UsersView usersView;
	@Autowired
	private NotificationService notifyService;

	@RequestMapping("/logout-success")
	public String logout(User user) {
		notifyService.addInfoMessage("You have been logout successfully");
		return "login";
	}

	@RequestMapping("/login")
	public String loginPage(User user) {
		return "login";
	}
	
	@RequestMapping("/login-error")
	public String loginPageError(User user) {
		notifyService.addErrorMessage("Invalid convination of Username and Password!");
		return "login";
	}
	@RequestMapping("/login-success")
	public String loginPageSucces(User user) {
		notifyService.addInfoMessage("Login successful");
		return "redirect:/";
	}

//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login(@Valid User user, BindingResult bindingResult, Model model) {
//		if (bindingResult.hasErrors()) {
//			notifyService.addErrorMessage("Please fill the form correctly!");
//			return "login";
//		}
//		long userCod = -1;
//		if ((userCod = usersView.authenticate(user.getUsername(), user.getPassword())) == -1) {
//			notifyService.addErrorMessage("Invalid Username or Password!");
//			return "login";
//		}
//		model.addAttribute("loggedUser", userCod);
//		notifyService.addInfoMessage("Login successful");
//		return "redirect:/";
//	}

	@RequestMapping("/register")
	public String registerPage(User user, Model model) {
		model.addAttribute("internalMode", "saving");
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			notifyService.addErrorMessage("Please fill the form correctly!");
			model.addAttribute("internalMode", "saving");
			return "register";
		}
		long save;
		if ((save = usersView.save(user)) != 0) {
			if (save == -2)
				notifyService.addErrorMessage("User already exist!");
			if (save == -1)
				notifyService.addErrorMessage("Unknow Error :c");
			model.addAttribute("internalMode", "saving");
			return "register";
		}

		notifyService.addInfoMessage("Sing up successful");
		return "redirect:/login";
	}

	@RequestMapping(value = "/profile")
	public String profile(User user, Model model) {
		model.addAttribute("internalMode", "editing");
		User find = usersView.findOne(1l);
		if (find == null) {
			notifyService.addErrorMessage("User not found!");
			return "redirect:/";
		}
		model.addAttribute("user", find);
		return "register";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.PATCH)
	public String profile(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			notifyService.addErrorMessage("Please fill the form correctly!");
			model.addAttribute("internalMode", "editing");
			return "register";
		}
		long save;
		if ((save = usersView.edit(user)) != 0) {
			if (save == -1)
				notifyService.addErrorMessage("Unknow Error :c");
			model.addAttribute("internalMode", "editing");
			return "register";
		}

		notifyService.addInfoMessage("Modifications complete!");
		return "redirect:/";
	}
}
