package com.sit331.artgallery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Optional;
import com.sit331.artgallery.dto.ArtifactDTO;
import com.sit331.artgallery.dto.BidNoArtifactDTO;
import com.sit331.artgallery.entities.ArtType;
import com.sit331.artgallery.entities.Artifact;
import com.sit331.artgallery.entities.Bid;
import com.sit331.artgallery.entities.Artifact;
import com.sit331.artgallery.services.ArtifactService;
import com.sit331.artgallery.services.BidService;
import com.sit331.artgallery.services.ArtifactService;
import com.sit331.artgallery.util.verificationUtil;

import jakarta.validation.Valid;

@RestController
public class ArtifactController {
	@Autowired
	private ArtifactService artifactService;
	
	@Autowired
	private BidService bidService;
	
	private List<ArtifactDTO> AllArtifacts; 
	
	@GetMapping("/api/artifacts")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<ArtifactDTO> getArtifacts() {
		AllArtifacts = artifactService.getAllArtifacts();
		return AllArtifacts;
	}
	
	@GetMapping("/api/artifacts/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ArtifactDTO getArtifact(@PathVariable("id") int id) {
		return AllArtifacts.get(id);
	}
	
	@PostMapping("/api/artifacts")
	public ResponseEntity<?> createArtifact(@RequestBody @Valid ArtifactDTO newArtifact) {
		AllArtifacts = artifactService.getAllArtifacts();
		if (verificationUtil.stringHasValue(newArtifact.getName()) 
				&& verificationUtil.floatHasValue(newArtifact.getPrice()) 
				&& newArtifact.getArtist() != null 
				&& !newArtifact.getArtTypes().isEmpty()
			) 
		{
			Artifact createdArtifact = artifactService.createArtifact(newArtifact);
	        return new ResponseEntity<>(createdArtifact, HttpStatus.CREATED);	
		}
        return new ResponseEntity<>("Name, Price, Artist cannot be empty", HttpStatus.BAD_REQUEST);	
	}
	
	
	@PutMapping("/api/artifacts")
	public ResponseEntity<?> updateArtifact(@RequestBody Artifact updatedArtifact) {
		AllArtifacts = artifactService.getAllArtifacts();
		if (verificationUtil.stringHasValue(updatedArtifact.getName()) 
				&& verificationUtil.floatHasValue(updatedArtifact.getPrice()) 
				&& updatedArtifact.getArtist() != null 
				&& !updatedArtifact.getArtTypes().isEmpty()
			) 
		{
			Artifact updatedArtifact1 = artifactService.updateArtifact(updatedArtifact);
	        return new ResponseEntity<>(updatedArtifact1, HttpStatus.CREATED);	
		}
        return new ResponseEntity<>("Body and Title should not be empty", HttpStatus.BAD_REQUEST);	
	}
	
	@PostMapping("/api/artifacts/{id}/bid")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> addBid(@RequestBody Bid newBid, @PathVariable("id") int id)
	{
		Artifact artifact = artifactService.getArtifactById(id);
		newBid.setBidArtifact(artifact);
		BidNoArtifactDTO newBidDTO = new BidNoArtifactDTO(bidService.createBid(newBid));
        return new ResponseEntity<>(newBidDTO, HttpStatus.OK);	
	}
	
	@DeleteMapping("/api/artifacts/{id}")
	public ResponseEntity<String> deleteArtifact(@PathVariable("id") int id) {
		AllArtifacts = artifactService.getAllArtifacts();
		if (artifactService.deleteArtifact(id)) {
			return new ResponseEntity<String>("Successfully deleted", HttpStatus.NO_CONTENT);	
		}
		return new ResponseEntity<String>("Artifact Not Found", HttpStatus.NOT_FOUND);
	}
}
