package webpage.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webpage.views.entities.User;


@Repository
public interface UserRepository  extends JpaRepository<User,Long>{

	public User findByUsername(String username);
}
