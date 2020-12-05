package hu.bme.aut.netcar;


//import org.springframework.data.geo.Coord;

import javax.persistence.*;


@Entity (name = "user_entity") // This tells Hibernate to make a table out of this class
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Car car;

	@Column(columnDefinition="TEXT")
	private String picture;

	private String username, email, password;
	private Integer credits;

	private Boolean valid,visible;

	private Coord location;


	private Boolean isInProgress;

	public Boolean getIsInProgress() {
		return this.isInProgress;
	}

	public void setIsInProgress(Boolean isInProgress) {
		this.isInProgress = isInProgress;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Coord getLocation() {
		return location;
	}

	public void setLocation(Coord destinationPos) {
		this.location = destinationPos;
	}

	public Integer getNumberOfRatings() {
		return numberOfRatings;
	}

	public void setNumberOfRatings(Integer numberOfRatings) {
		this.numberOfRatings = numberOfRatings;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	private Integer numberOfRatings;
	private Double rating;


	public User() {

	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String name) {
		this.username = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicture() {
		return picture;
	}
	public void setPicture(String pictureUrl) {
		this.picture = pictureUrl;
	}

	public Integer getCredits() {
		return credits;
	}
	public void setCredits(Integer credits) {
		this.credits = credits;
	}


	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}
}