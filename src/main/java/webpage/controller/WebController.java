package webpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import webpage.views.SongsView;

@Controller
public class WebController {

	@Autowired
	private SongsView songsView;

	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("songs", songsView.findPublics());
		model.addAttribute("latest", songsView.findLatest());
		model.addAttribute("user", 1);
		return "welcome";
	}


}
