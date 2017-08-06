package webpage.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import webpage.model.UserRepository;
import webpage.views.entities.User;

@Component
public class UsersView {

	@Autowired
	UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public long authenticate(String username, String password) {
		User find = userRepo.findByUsername(username);
		if (find != null && find.getPassword().equals(password))
			return find.getCod();
		return -1;
	}

	public long save(User user) {
		try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userRepo.save(user);
		} catch (DataIntegrityViolationException e) {
			return -2;
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}

	public User findOne(long cod) {
		return userRepo.findOne(cod);
	}

	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	public long edit(User user) {
		try {
			userRepo.save(user);
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}
}
