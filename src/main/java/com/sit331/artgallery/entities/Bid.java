package com.sit331.artgallery.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sit331.artgallery.dto.BidNoArtifactDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;

@Entity
public class Bid {

	public Bid() {}
	
	public Bid(int id, float originalPrice, float newBid, String newBidder, Artifact bidArtifact,
			LocalDateTime timestamp) {
		super();
		this.id = id;
		this.originalPrice = originalPrice;
		this.newBid = newBid;
		this.newBidder = newBidder;
		this.bidArtifact = bidArtifact;
		this.timestamp = timestamp;
	}
	
	public Bid(BidNoArtifactDTO bidDTO) {
		super();
		this.id = bidDTO.getId();
		this.originalPrice = bidDTO.getOriginalPrice();
		this.newBid = bidDTO.getNewBid();
		this.newBidder = bidDTO.getNewBidder();
//		this.bidArtifact = bidArtifact;
		this.timestamp = timestamp;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	private float originalPrice;
	private float newBid;
	@Email
	private String newBidder;
	
	@ManyToOne
	private Artifact bidArtifact;
	
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm")
	private LocalDateTime timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}

	public float getNewBid() {
		return newBid;
	}

	public void setNewBid(float newBid) {
		this.newBid = newBid;
	}

	public String getNewBidder() {
		return newBidder;
	}

	public void setNewBidder(String newBidder) {
		this.newBidder = newBidder;
	}

	public Artifact getBidArtifact() {
		return bidArtifact;
	}

	public void setBidArtifact(Artifact bidArtifact) {
	    this.bidArtifact = bidArtifact;
	    
//	    if (bidArtifact != null && !bidArtifact.getBids().contains(this)) {
//	        bidArtifact.getBids().add(this);
//	    }
	    }

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
