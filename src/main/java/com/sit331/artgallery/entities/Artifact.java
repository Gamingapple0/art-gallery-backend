package com.sit331.artgallery.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Artifact {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@JsonProperty("id")
	private Integer id;
	
	@Column(unique=true)
	private String Name;
	
	private Float Price;
	
	private String ImgURL;
	
	@OneToMany
	private List<ArtType> ArtTypes;
	
    @ManyToOne
	@JoinColumn(name = "artist_id")
	private Artist artist;
	
}
