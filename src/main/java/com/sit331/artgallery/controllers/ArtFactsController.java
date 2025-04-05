package com.sit331.artgallery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sit331.artgallery.entities.ArtFact;
import com.sit331.artgallery.services.ArtFactService;
import com.sit331.artgallery.util.verificationUtil;

@RestController
public class ArtFactsController {
	@Autowired
	private ArtFactService artFactService;
	
	private List<ArtFact> AllArtFacts; 
	
	@GetMapping("/api/art-facts")
	public List<ArtFact> getArtFacts() {
		AllArtFacts = artFactService.getAllArtFacts();
		return AllArtFacts;
	}
	
	@PostMapping("/api/art-facts")
	public ResponseEntity<?> createArtFact(@RequestBody ArtFact newArtFact) {
		AllArtFacts = artFactService.getAllArtFacts();
		if (verificationUtil.stringHasValue(newArtFact.getFactBody()) && verificationUtil.stringHasValue(newArtFact.getFactTitle())) {
			ArtFact createdArtFact = artFactService.createArtFact(newArtFact);
	        return new ResponseEntity<>(createdArtFact, HttpStatus.CREATED);	
		}
        return new ResponseEntity<>("Body and Title should not be empty", HttpStatus.BAD_REQUEST);	
	}
	
	
	@PutMapping("/api/art-facts")
	public ResponseEntity<?> updatedArtFact(@RequestBody ArtFact updatedArtFact) {
		AllArtFacts = artFactService.getAllArtFacts();
		if (verificationUtil.stringHasValue(updatedArtFact.getFactBody()) && verificationUtil.stringHasValue(updatedArtFact.getFactTitle())) {
			ArtFact updatedArtFact1 = artFactService.updateArtFact(updatedArtFact);
	        return new ResponseEntity<>(updatedArtFact1, HttpStatus.CREATED);	
		}
        return new ResponseEntity<>("Body and Title should not be empty", HttpStatus.BAD_REQUEST);	
	}
	
	@DeleteMapping("/api/art-facts/{id}")
	public ResponseEntity<String> deleteArtFact(@PathVariable("id") int id) {
		AllArtFacts = artFactService.getAllArtFacts();
		if (artFactService.deleteArtFact(id)) {
			return new ResponseEntity<String>("Successfully deleted", HttpStatus.NO_CONTENT);	
		}
		return new ResponseEntity<String>("Art Fact does not exist", HttpStatus.NOT_FOUND);
	}
}
