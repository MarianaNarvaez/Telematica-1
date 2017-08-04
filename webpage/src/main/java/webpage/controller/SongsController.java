package webpage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import webpage.model.SongsRepository;
import webpage.model.UserRepository;
import webpage.views.SongsView;
import webpage.views.entities.Song;
import webpage.views.entities.User;

@Controller
public class SongsController {

	@Autowired
	private SongsView songsView;
	@Autowired
	SongsRepository songRepo;
	@Autowired
	UserRepository userRepo;

	
	@RequestMapping(value = "/song", method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(songRepo.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/song", method = RequestMethod.POST)
	public ResponseEntity<?> saveSong(@RequestBody List<Song> songs) {
		songRepo.save(songs);
		return new ResponseEntity<>(HttpStatus.OK);
	}

//	@RequestMapping(value = "/song/{cod}", method = RequestMethod.GET)
//	public ResponseEntity<?> findOne(@PathVariable("cod") long cod, Model model) {
//		if(model==null){
//			return new ResponseEntity<>(songRepo.findOne(cod), HttpStatus.OK);
//		}
//		model.addAttribute("song", songRepo.findOne(cod));
//		return new ResponseEntity<>("song/view", HttpStatus.OK);
//	}
//	@RequestMapping(value = "/{cod}", method = RequestMethod.GET)
//	public String findOne(@PathVariable("cod") long cod, Model model) {
//		model.addAttribute("song", songRepo.findOne(cod));
//		return "song/view";
//	}

	@RequestMapping(value = "/song/{cod}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteSong(@PathVariable("cod") long cod) {
		songRepo.delete(cod);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/song/title", method = RequestMethod.POST)
	public ResponseEntity<?> findByName(@RequestParam("title") String title) {
		return new ResponseEntity<>(songRepo.findByTitleIgnoreCaseContaining(title), HttpStatus.OK);
	}

	@RequestMapping(value = "/song/savetest", method = RequestMethod.POST)
	public ResponseEntity<?> saveTest(@RequestBody List<Song> songs) {
		for (Song s : songs) {
			s.setAlbumName("pruebitas");
			songsView.saveTest(s);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user) {
		userRepo.save(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
