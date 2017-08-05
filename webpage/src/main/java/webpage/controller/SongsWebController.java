package webpage.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webpage.views.SongsView;
import webpage.views.entities.Song;

@Controller
public class SongsWebController {

	@Autowired
	private SongsView songsView;
	@Autowired
	private NotificationService notifyService;

	@RequestMapping(value = "/collection")
	public String findCollection(Model model) {
		model.addAttribute("songs", songsView.findAll());
		model.addAttribute("latest", songsView.findAll());
		return "/collection";
	}

	@RequestMapping(value = "/song", method = RequestMethod.GET)
	public String newSong(Song song, Model model) {
		model.addAttribute("internalMode", "saving");
		return "/song/save";
	}

	@RequestMapping(value = "/song", method = RequestMethod.POST)
	public String saveSong(@Valid Song song, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			notifyService.addErrorMessage("Please fill the form correctly!");
			return "/song/save";
		}
		long save;
		if ((save = songsView.save(song)) != 0) {
			if (save == -1)
				notifyService.addErrorMessage("Unknow Error :c");
			return "/song/save";
		}

		notifyService.addInfoMessage("Song added successfully");
		return "redirect:/collection";
	}

	@RequestMapping("/song/{cod}")
	public String viewSong(@PathVariable("cod") long cod, Model model) {
		Song song = songsView.findOne(cod);
		if (song == null) {
			notifyService.addErrorMessage("Cannot find song #" + cod);
			return "redirect:/";
		}
		model.addAttribute("song", song);

		return "song/view";
	}

	@RequestMapping(value = "/song/{cod}", method = RequestMethod.DELETE)
	public String deleteSong(@PathVariable("cod") long cod) {
		long save;
		if ((save = songsView.delete(cod)) != 0) {
			if (save == -1)
				notifyService.addErrorMessage("Unknow Error :c");
			if (save == -2)
				notifyService.addErrorMessage("Title already exists!");
			return "/song/view";
		}

		notifyService.addInfoMessage("Song deleted successfully");
		return "redirect:/collection";
	}

	@RequestMapping(value = "/song/{cod}", method = RequestMethod.PUT)
	public String edit(@PathVariable("cod") long cod, Song song, Model model) {
		model.addAttribute("internalMode", "editing");

		model.addAttribute(songsView.findOne(cod));
		return "song/save";
	}

	@RequestMapping(value = "/song/{cod}", method = RequestMethod.PATCH)
	public String edit(@PathVariable("cod") long cod, @Valid Song song, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			notifyService.addErrorMessage("Please fill the form correctly!");
			model.addAttribute("internalMode", "editing");
			return "/song/save";
		}
		long save;
		if ((save = songsView.edit(song)) != 0) {
			if (save == -1)
				notifyService.addErrorMessage("Unknow Error :c");
			model.addAttribute("internalMode", "editing");
			return "/song/save";
		}

		notifyService.addInfoMessage("Modifications complete!");
		return "redirect:/song/{cod}";
	}

	// @RequestMapping(value = "/song/title", method = RequestMethod.POST)
	// public ResponseEntity<?> findByName(@RequestParam("title") String title)
	// {
	// return new
	// ResponseEntity<>(songRepo.findByTitleIgnoreCaseContaining(title),
	// HttpStatus.OK);
	// }

}
