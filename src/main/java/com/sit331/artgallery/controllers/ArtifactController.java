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

import com.sit331.artgallery.dto.ArtifactDTO;
import com.sit331.artgallery.entities.ArtType;
import com.sit331.artgallery.entities.Artifact;
import com.sit331.artgallery.entities.Artifact;
import com.sit331.artgallery.services.ArtifactService;
import com.sit331.artgallery.services.ArtifactService;
import com.sit331.artgallery.util.verificationUtil;

import jakarta.validation.Valid;

@RestController
public class ArtifactController {
	@Autowired
	private ArtifactService artifactService;
	
	private List<ArtifactDTO> AllArtifacts; 
	
	@GetMapping("/artifacts")
	public List<ArtifactDTO> getArtifacts() {
		AllArtifacts = artifactService.getAllArtifacts();
		return AllArtifacts;
	}
	
	@PostMapping("/artifacts")
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
	
	
	@PutMapping("/artifacts")
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
	
	@DeleteMapping("/artifacts/{id}")
	public ResponseEntity<String> deleteArtifact(@PathVariable("id") int id) {
		AllArtifacts = artifactService.getAllArtifacts();
		if (artifactService.deleteArtifact(id)) {
			return new ResponseEntity<String>("Successfully deleted", HttpStatus.NO_CONTENT);	
		}
		return new ResponseEntity<String>("Artifact Not Found", HttpStatus.NOT_FOUND);
	}
}
