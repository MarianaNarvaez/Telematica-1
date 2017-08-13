package webpage.views.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="MATEO_SONG")
public class Song {

	@Id
	@JsonIgnore
	@Column(unique=true, nullable=false,precision=10, scale=0)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cod;
	@OrderBy
	@NotNull
	@Column(nullable=false,length=256,unique=true)
	private String title;
	@Column(nullable=false)
	private String owner;
	@Column(nullable=false)
	private char publicContent;
	@Column(nullable=false)
	private String path;

	
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the publicContent
	 */
	public char getPublicContent() {
		return publicContent;
	}

	/**
	 * @param publicContent the publicContent to set
	 */
	public void setPublicContent(char publicContent) {
		this.publicContent = publicContent;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
