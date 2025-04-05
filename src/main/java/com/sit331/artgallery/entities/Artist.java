package com.sit331.artgallery.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sit331.artgallery.dto.ArtistBasicDTO;
import com.sit331.artgallery.dto.ArtistDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Artist {

	public Artist() {}
	
	public Artist(Integer id, String firstName, String lastName, LocalDate dOB, String email, String bio, boolean isMale,
			List<Artifact> artWorks, String imgURL) {
		super();
		this.id = id;
		FirstName = firstName;
		LastName = lastName;
		DOB = dOB;
		this.email = email;
		Bio = bio;
		this.isMale = isMale;
		this.artWorks = artWorks;
		ImgURL = imgURL;
	}
	
	public Artist(ArtistDTO artistDTO) {
		super();
		this.id = artistDTO.getId();
		FirstName = artistDTO.getFirstName();
		LastName = artistDTO.getLastName();
		DOB = artistDTO.getDob();
		this.email = artistDTO.getEmail();
		Bio = artistDTO.getBio();
		this.isMale = artistDTO.isMale();
		this.artWorks = artistDTO.getArtistArtifacts();
		ImgURL = artistDTO.getImgURL();
	}
	
	public Artist(ArtistBasicDTO artistDTO) {
		super();
		this.id = artistDTO.getId();
		FirstName = artistDTO.getFirstName();
		LastName = artistDTO.getLastName();
		DOB = artistDTO.getDob();
		this.email = artistDTO.getEmail();
		Bio = artistDTO.getBio();
		this.isMale = artistDTO.isMale();
		this.artWorks = null;
		ImgURL = artistDTO.getImgURL();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public LocalDate getDOB() {
		return DOB;
	}

	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBio() {
		return Bio;
	}

	public void setBio(String bio) {
		Bio = bio;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public List<Artifact> getArtWorks() {
		return artWorks;
	}

	public void setArtWorks(List<Artifact> artWorks) {
		this.artWorks = artWorks;
	}

	public String getImgURL() {
		return ImgURL;
	}

	public void setImgURL(String imgURL) {
		ImgURL = imgURL;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@JsonProperty("id")
	private Integer id;
	
	private String FirstName;
	
	private String LastName;
	
    @JsonFormat(pattern = "yyyy-MM-dd") 
	private LocalDate DOB;
	
	@Column(unique=true)
	private String email;
	
	private String Bio;
	
	private boolean isMale;
	
    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Artifact> artWorks;
	
	private String ImgURL;
}
