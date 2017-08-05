package webpage.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webpage.views.entities.Song;


@Repository
public interface SongsRepository  extends JpaRepository<Song,Long>{
	
	public List<Song> findAllByOrderByTitleAsc();	
	public List<Song> findByTitleIgnoreCaseContaining(String title);
	public List<Song> findAllByOwner(long owner);
}
