package com.sit331.artgallery.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sit331.artgallery.entities.Artifact;
import com.sit331.artgallery.entities.Bid;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;

public class BidNoArtifactDTO {
	public BidNoArtifactDTO() {}
	
	public BidNoArtifactDTO(int id, float originalPrice, float newBid, String newBidder, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.originalPrice = originalPrice;
		this.newBid = newBid;
		this.newBidder = newBidder;
		this.timestamp = timestamp;
	}
	
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

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public BidNoArtifactDTO(Bid bid) {
		super();
		this.id = bid.getId();
		this.originalPrice = bid.getOriginalPrice();
		this.newBid = bid.getNewBid();
		this.newBidder = bid.getNewBidder();
		this.timestamp = bid.getTimestamp();
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	private float originalPrice;
	private float newBid;
	@Email
	private String newBidder;
	
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm")
	private LocalDateTime timestamp;
}
