package webpage.views.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="MATEO_USER")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long cod;
	@NotNull
	@Size(min=6, max=12, message = "Username size should be in the range [6...12]")
	@Column(unique=true)
	private String username;
	@NotNull
	@Size(min=8, max=60, message = "Password size should be in the range [8...60]")
	private String password;
//	@NotNull
	@Email
	private String email;

	protected User(){}

	public User(int i){
		switch (i){
		case 1:
			password="12345678";
			break;
		case 2:
			username="Estaaaaa";
			password="12345678";
			break;
		case 3:
			username="Lorraaaaaa";
			password="12345678";
			break;
		}
		
	}
	
	/**
	 * @return the cod
	 */
	public long getCod() {
		return cod;
	}
	/**
	 * @param cod the cod to set
	 */
	public void setCod(long cod) {
		this.cod = cod;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	

}
