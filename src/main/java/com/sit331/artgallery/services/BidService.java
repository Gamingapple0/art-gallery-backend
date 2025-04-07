package com.sit331.artgallery.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sit331.artgallery.dto.BidNoArtifactDTO;
import com.sit331.artgallery.entities.Artifact;
import com.sit331.artgallery.entities.Bid;
import com.sit331.artgallery.repo.ArtifactRepo;
import com.sit331.artgallery.repo.BidRepo;
import com.sit331.artgallery.util.verificationUtil;

import jakarta.transaction.Transactional;

@Service
public class BidService {
	@Autowired
	private BidRepo repo; 
	
	@Autowired
	private ArtifactRepo artifactRepository;
	
	public List<Bid> getAllBids() {
		return repo.findAll();		
	}
	
	@Transactional
	public Bid createBid(Bid newBid) {
        Optional<Artifact> artifactO = artifactRepository.findById(newBid.getBidArtifact().getId());
		if (newBid.getNewBid() > newBid.getOriginalPrice() 
				&& artifactO.isPresent()
				&& verificationUtil.stringHasValue(newBid.getNewBidder())
				&& verificationUtil.floatHasValue(newBid.getOriginalPrice())
				) 
		{
			newBid.setTimestamp(LocalDateTime.now());
			Artifact artifact = artifactO.get();
			newBid.setBidArtifact(artifact);
			Bid createdBid = repo.save(newBid);
			artifact.getBids().add(createdBid);
			artifactRepository.save(artifact);
			return createdBid;
		}
		throw new IllegalArgumentException("Unknown Error when making a bid");
	}

}
