package webpage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webpage.model.SongsRepository;
import webpage.model.UserRepository;
import webpage.views.entities.Song;
import webpage.views.entities.User;

@Controller
public class SongsJsonController {

	@Autowired
	SongsRepository songRepo;
	@Autowired
	UserRepository userRepo;

	@RequestMapping(value = "/findAllSong", method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(songRepo.findAll(), HttpStatus.OK);
	}
	@RequestMapping(value = "/findAllUser", method = RequestMethod.GET)
	public ResponseEntity<?> findAlluser() {
		return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/songstest", method = RequestMethod.POST)
	public ResponseEntity<?> saveSong(@RequestBody List<Song> songs) {
		songRepo.save(songs);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
	//
	// @RequestMapping(value = "/song/{cod}", method = RequestMethod.DELETE)
	// public ResponseEntity<?> deleteSong(@PathVariable("cod") long cod) {
	// songRepo.delete(cod);
	// return new ResponseEntity<>(HttpStatus.OK);
	// }
	//
	// @RequestMapping(value = "/song/title", method = RequestMethod.GET)
	// public ResponseEntity<?> findByName(@RequestParam("title") String title)
	// {
	// return new
	// ResponseEntity<>(songRepo.findByTitleIgnoreCaseContaining(title),
	// HttpStatus.OK);
	// }
	//

}
