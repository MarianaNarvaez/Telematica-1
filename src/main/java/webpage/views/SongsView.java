package webpage.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
		return songRepo.findAllByOrderByTitleAsc();
	}
	
	public long save(Song song){
		try {
			songRepo.save(song);
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}

	public long delete(long song){
		try {
			songRepo.delete(song);
		} catch (DataIntegrityViolationException e) {
			return -2;
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}
	
	public long edit(Song song){
		try {
			songRepo.save(song);
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}
	
	public Song findOne(long cod){
		return songRepo.findOne(cod);
	}
	public List<Song> findByOwner(String username){
		return songRepo.findByOwnerOrderByTitleAsc(username);
	}
	public List<Song> findLatest(){
		return songRepo.findLatest();
	}
	public List<Song> findPublics(){
		return songRepo.findPublics();
	}
	public List<Song> findByTitle(String title){
		return songRepo.findByTitle(title);
	}
	public List<Song> findByTitleOwner(String title,String owner){
		return songRepo.findByTitleAndOwner(title,owner);
	}
}
