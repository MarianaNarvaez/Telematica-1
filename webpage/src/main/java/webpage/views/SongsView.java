package webpage.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import webpage.model.SongsRepository;
import webpage.views.entities.Song;


@Component
public class SongsView {

	@Autowired
	SongsRepository songRepo;

	public long saveTest(Song song) {
		songRepo.save(song);
		return 0;
	}
	
	public List<Song> findAll(){
		return songRepo.findAll();
	}

	public Song findOne(long cod){
		return songRepo.findOne(cod);
	}
}
