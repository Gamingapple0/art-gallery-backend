package com.sit331.artgallery.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="art_types")
public class ArtType {

	public ArtType() {}
	
	public ArtType(Integer id, String name, String description, String creationMethod, String culturalGroup,
			String region) {
		super();
		this.id = id;
		Name = name;
		Description = description;
		CreationMethod = creationMethod;
		CulturalGroup = culturalGroup;
		Region = region;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getCreationMethod() {
		return CreationMethod;
	}

	public void setCreationMethod(String creationMethod) {
		CreationMethod = creationMethod;
	}

	public String getCulturalGroup() {
		return CulturalGroup;
	}

	public void setCulturalGroup(String culturalGroup) {
		CulturalGroup = culturalGroup;
	}

	public String getRegion() {
		return Region;
	}

	public void setRegion(String region) {
		Region = region;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@JsonProperty("id")
	private Integer id;
	
	@Column(unique=true)
	private String Name;
	
	private String Description;
	
	private String CreationMethod;
	
	private String CulturalGroup;
	
	private String Region;
}
