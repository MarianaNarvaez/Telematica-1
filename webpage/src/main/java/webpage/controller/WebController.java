package webpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import webpage.views.SongsView;
import webpage.views.entities.Song;

@Controller
public class WebController {

	@Autowired
	private SongsView songsView;
	@Autowired
    private NotificationService notifyService;
	private String message = "Alohaaa";

	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("message", this.message);
		model.addAttribute("songs", songsView.findAll());
		model.addAttribute("latest", songsView.findAll());
		model.addAttribute("user", 1);
		return "welcome";
	}

	@RequestMapping("/save")
	public String save(Model model) {
		model.addAttribute("message", "Esto es para el save");
		return "save";
	}

	
	@RequestMapping("/song/{cod}")
	public String viewSong(@PathVariable("cod") long cod,Model model) {
		Song song = songsView.findOne(cod);
		if (song == null) {
            notifyService.addErrorMessage("Cannot find song #" + cod);
            return "redirect:/";
        }
		model.addAttribute("song",song);
		
		return "song/view";
	}
}
