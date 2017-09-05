package webpage.views;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import webpage.model.SongsRepository;
import webpage.views.entities.Song;

@Component
public class SongsView {

	@Autowired
	SongsRepository songRepo;

	public List<Song> findAll() {
		return songRepo.findAllByOrderByTitleAsc();
	}

	public int save(List<MultipartFile> songs, char publicContent, String username) {

//		if (songs.isEmpty() || songs == null || songs.size() == 0)
//			return -4;

		final String path = App.songsPath + File.separator + username + File.separator;
		File x = new File(path);
		try {
			if (!x.exists()) {
				if(!x.mkdirs())return -3;
			}
		} catch (Exception e) {
			return -3;
		}
		List<String> names;
		if ((names = saveFiles(songs, path)) == null)
			return -2;
		try {
			List<Song> songList = new ArrayList<Song>();
			for (MultipartFile file : songs) {
				Song song = new Song();
				String name = file.getOriginalFilename();
				song.setTitle(name.substring(0, name.indexOf('.')));
				song.setOwner(username);
				song.setPublicContent(publicContent);
				song.setPath(path + name);
				songList.add(song);
			}
			songRepo.save(songList);
		} catch (DataIntegrityViolationException e) {
			return -5;
		} catch (Exception e) {
			deleteFiles(names, path);
			return -1;
		}
		return 0;
	}

	public List<String> saveFiles(List<MultipartFile> songs, String path) {
		List<String> names = new ArrayList<String>();
		for (MultipartFile song : songs) {
			try {
				File file = new File((path + song.getOriginalFilename()));
				song.transferTo(file);
				names.add(song.getOriginalFilename());
			} catch (Exception e) {
				deleteFiles(names, path);
				return null;
			}
		}
		return names;
	}

	public void deleteFiles(List<String> names, String path) {
		for (String name : names) {
			File file = new File(path + name);
			try {
				file.delete();
			} catch (Exception e) {
			}
		}
	}

	public long delete(long id, String username) {

		try {
			Song song = songRepo.findOne(id);
			if (song == null)
				return -1;
			if (!song.getOwner().equals(username))
				return -2;
			songRepo.delete(song);
			File file = new File(song.getPath());
			if(file.delete()){
				return 0;
			}else{
				return -3;
			}
		} catch (Exception e) {
			return -3;
		}
	}

	public long edit(Song song, String oldTitle, String username) {
		try {
			if (!username.equals(song.getOwner()))
				return -3;
			final String path = App.songsPath + File.separator + username + File.separator;
			song.setPath(path + song.getTitle() + ".mp3");
			songRepo.save(song);
			Path source = Paths.get(path + oldTitle + ".mp3");
			Path target = Paths.get(song.getPath());
			Files.move(source, target);
		} catch (DataIntegrityViolationException e) {
			return -2;
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}

	public File findFile(long cod, String username) throws FileNotFoundException {
		Song song = songRepo.findOne(cod);
		if (song == null)
			throw new FileNotFoundException();
		File file = new File(song.getPath());
		if (file.exists()) {
			return file;
		} else {
			throw new FileNotFoundException();
		}
	}

	public Song findOne(long cod) {
		return songRepo.findOne(cod);
	}

	public List<Song> findByOwner(String username) {
		return songRepo.findByOwnerOrderByTitleAsc(username);
	}

	public List<Song> findLatest() {
		return songRepo.findLatest();
	}

	public List<Song> findPublics() {
		return songRepo.findPublics();
	}

	public List<Song> findByTitle(String title) {
		return songRepo.findByTitle(title);
	}

	public List<Song> findByTitleOwner(String title, String owner) {
		return songRepo.findByTitleAndOwner(title, owner);
	}
}
