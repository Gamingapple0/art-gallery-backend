package com.sit331.artgallery.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sit331.artgallery.entities.Artist;

import jakarta.validation.constraints.Email;

public class ArtistBasicDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String bio;
    @Email
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd") 
    private LocalDate dob; 
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	private String imgURL;
    private boolean isMale;
    
    public ArtistBasicDTO() {}

    // Constructor
    public ArtistBasicDTO(Artist artist) {
        this.id = artist.getId();
        this.firstName = artist.getFirstName();
        this.lastName = artist.getLastName();
        this.dob = artist.getDOB();
        this.email = artist.getEmail();
        this.imgURL = artist.getImgURL();
        this.isMale = artist.isMale();
        this.bio = artist.getBio();
    }
}
