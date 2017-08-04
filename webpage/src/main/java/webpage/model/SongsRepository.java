package webpage.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webpage.views.entities.Song;


@Repository
public interface SongsRepository  extends JpaRepository<Song,Long>{
	public List<Song> findAllByOrderByTitleAsc();
	
//	@Query("Select s from Song s where s.title like %:title%")
//	public List<Song> findByTitleLike(@Param("title")String title);
	
	public List<Song> findByTitleIgnoreCaseContaining(String title);
}
