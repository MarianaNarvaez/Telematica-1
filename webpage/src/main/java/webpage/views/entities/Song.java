package webpage.views.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

@Entity
public class Song {

	@Id
	//@JsonIgnore
	@Column(unique=true, nullable=false,precision=10, scale=0)
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(generator = "SONG_S", strategy = GenerationType.SEQUENCE)
	//@SequenceGenerator(name = "SONG_S", sequenceName = "SONG_S",allocationSize=1)
	private long cod;
	@OrderBy
	@NotNull
	@Column(nullable=false,length=256,unique=true)
	private String title;
	@Column(nullable=true,length=256)
	private String albumName;
	@Column(nullable=true,length=256)
	private String artistName;
	@Column(nullable=true,length=256)
	private String genre;
	@NotNull
	@Column(nullable=false,precision=5, scale=0)
	private Integer duration;
	@Column(nullable=true)
	private Date year;
	@Column(nullable=false)
	private long owner;
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
	 * @return the albumName
	 */
	public String getAlbumName() {
		return albumName;
	}
	/**
	 * @param albumName the albumName to set
	 */
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	/**
	 * @return the artistName
	 */
	public String getArtistName() {
		return artistName;
	}
	/**
	 * @param artistName the artistName to set
	 */
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	/**
	 * @return the duration
	 */
	public Integer getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	/**
	 * @return the year
	 */
	public Date getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(Date year) {
		this.year = year;
	}
	/**
	 * @return the owner
	 */
	public long getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(long owner) {
		this.owner = owner;
	}

//	@ManyToOne(targetEntity = (User.class))
//	@JoinColumn(name = "COD", insertable = false, updatable = false)
//	//@Column(nullable=false,precision=10, scale=0)
//	private User owner;
		
	
}
