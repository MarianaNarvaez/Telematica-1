package webpage.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webpage.views.entities.Song;


@Repository
public interface SongsRepository  extends JpaRepository<Song,Long>{
	
	public List<Song> findAllByOrderByTitleAsc();	
	public List<Song> findByOwnerOrderByTitleAsc(String owner);

	@Query("select s from Song s where lower(s.title) like CONCAT('%',lower(:title),'%') and (s.publicContent='P' or s.owner=:owner) order by s.title")
	public List<Song> findByTitleAndOwner(@Param("title") String title,@Param("owner") String owner);
	
	@Query("select s from Song s where lower(s.title) like CONCAT('%',lower(:title),'%') and (s.publicContent='P') order by s.title")
	public List<Song> findByTitle(@Param("title") String title);
	
	@Query("select s from Song s where publicContent='P' order by s.title")
	public List<Song> findPublics();
	
	@Query("select s from Song s where s.cod > ((select MAX(cod) from Song b)-5) and s.publicContent='P'")
	public List<Song> findLatest();
}
