package com.sit331.artgallery.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


@Entity
@Table(name="art_facts")
public class ArtFact {

	public String getFactTitle() {
		return FactTitle;
	}

	public void setFactTitle(String factTitle) {
		FactTitle = factTitle;
	}

	public String getFactBody() {
		return FactBody;
	}

	public void setFactBody(String factBody) {
		FactBody = factBody;
	}

	public ArtFact() {}

	public ArtFact(Integer Id, String factTitle, String factBody) {
		id = Id; 
		FactTitle = factTitle;
		FactBody = factBody;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@JsonProperty("id")
	private Integer id;
	
	@Column(unique=true)
	private String FactTitle;
	
	private String FactBody;
}
