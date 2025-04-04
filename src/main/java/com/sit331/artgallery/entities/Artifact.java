package com.sit331.artgallery.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sit331.artgallery.dto.ArtifactDTO;
import com.sit331.artgallery.dto.ArtistBasicDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
	
	@ManyToMany
	private List<ArtType> ArtTypes;
	
	private String Description;

	@ManyToOne
	@JoinColumn(name = "artist_id")
	@JsonBackReference
	private Artist artist;

    public Artifact() {}
	public Artifact(Integer id, String name, Float price, String imgURL, List<ArtType> artTypes, Artist artist, String description) {
		super();
		this.id = id;
		Name = name;
		Price = price;
		ImgURL = imgURL;
		ArtTypes = artTypes;
		this.artist = artist;
		Description = description;
	}
	
    public Artifact(ArtifactDTO artifactDTO) {
		this.id = artifactDTO.getId();;
		Name = artifactDTO.getName();
		Price = artifactDTO.getPrice();
		ImgURL = artifactDTO.getImgURL();
		ArtTypes = artifactDTO.getArtTypes();
		this.artist = new Artist(artifactDTO.getArtist());
		Description = artifactDTO.getDescription();
    }

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Float getPrice() {
		return Price;
	}

	public void setPrice(Float price) {
		Price = price;
	}

	public String getImgURL() {
		return ImgURL;
	}

	public void setImgURL(String imgURL) {
		ImgURL = imgURL;
	}

	public List<ArtType> getArtTypes() {
		return ArtTypes;
	}

	public void setArtTypes(List<ArtType> artTypes) {
		ArtTypes = artTypes;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
}
