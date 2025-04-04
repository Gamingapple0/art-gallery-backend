package com.sit331.artgallery.dto;

import java.util.List;

import com.sit331.artgallery.entities.ArtType;
import com.sit331.artgallery.entities.Artifact;
import com.sit331.artgallery.entities.Artist;

public class ArtifactDTO {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private String imgURL;
    private List<ArtType> artTypes;
    private ArtistBasicDTO artist; // Reference artist without artifacts

    // Constructor
    public ArtifactDTO() {}
    
    public ArtifactDTO(Artifact artifact) {
        this.id = artifact.getId();
        this.name = artifact.getName();
        this.description = artifact.getDescription();
        this.price = artifact.getPrice();
        this.imgURL = artifact.getImgURL();
        this.artTypes = artifact.getArtTypes();
        this.artist = new ArtistBasicDTO(artifact.getArtist());
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public List<ArtType> getArtTypes() {
		return artTypes;
	}

	public void setArtTypes(List<ArtType> artTypes) {
		this.artTypes = artTypes;
	}

	public ArtistBasicDTO getArtist() {
		return artist;
	}

	public void setArtist(ArtistBasicDTO artist) {
		this.artist = artist;
	}
}
