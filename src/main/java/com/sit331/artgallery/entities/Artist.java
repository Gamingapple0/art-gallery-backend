package com.sit331.artgallery.entities;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Artist {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@JsonProperty("id")
	private Integer id;
	
	private String FirstName;
	
	private String LastName;
	
	private Date DOB;
	
	@Column(unique=true)
	private String Email;
	
	private String Bio;
	
	private boolean isMale;
	
	@OneToMany(mappedBy="artist")
	private List<Artifact> artWorks;
	
	private String ImgURL;
}
