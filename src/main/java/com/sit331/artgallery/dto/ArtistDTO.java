package com.sit331.artgallery.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sit331.artgallery.entities.Artifact;
import com.sit331.artgallery.entities.Artist;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class ArtistDTO {

	    private Integer id;
		@NotNull
	    private String firstName;
		@NotNull
	    private String lastName;
	    private String bio;
	    @Email
	    private String email;
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

		public List<Artifact> getArtistArtifacts() {
			return artistArtifacts;
		}

		public void setArtistArtifacts(List<Artifact> artistArtifacts) {
			this.artistArtifacts = artistArtifacts;
		}

		@JsonFormat(pattern = "yyyy-MM-dd")
		@NotNull
	    private LocalDate dob; 
	    private String imgURL;
	    @NotNull
	    private boolean isMale;
	    private List<Artifact> artistArtifacts;
	    
	    public ArtistDTO() {}
	    // Constructor
	    public ArtistDTO(Artist artist) {
	        this.id = artist.getId();
	        this.firstName = artist.getFirstName();
	        this.lastName = artist.getLastName();
	        this.dob = artist.getDOB();
	        this.email = artist.getEmail();
	        this.imgURL = artist.getImgURL();
	        this.isMale = artist.isMale();
	        this.bio = artist.getBio();
	        this.artistArtifacts = artist.getArtWorks();
	    }

}
