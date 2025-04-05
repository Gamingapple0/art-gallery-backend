package com.sit331.artgallery.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sit331.artgallery.dto.ArtistDTO;
import com.sit331.artgallery.entities.ArtType;
import com.sit331.artgallery.entities.Artifact;
import com.sit331.artgallery.entities.Artist;
import com.sit331.artgallery.services.ArtistService;
import com.sit331.artgallery.util.verificationUtil;

import jakarta.validation.Valid;

@RestController
public class ArtistController {

	@Autowired
	private ArtistService artistService;
	private List<Artist> AllArtists; 

	@GetMapping("/artists")
	public List<Artist> getArtist() {
		AllArtists = artistService.getAllArtists();
		return AllArtists;
	}
	@PostMapping("/artists")
	public ResponseEntity<?> createArtist(@Valid @RequestBody ArtistDTO newArtistDTO) {
		AllArtists = artistService.getAllArtists();
		if (verificationUtil.stringHasValue(newArtistDTO.getEmail()) 
				&& verificationUtil.stringHasValue(newArtistDTO.getFirstName())
				&& verificationUtil.stringHasValue(newArtistDTO.getLastName())
				&& newArtistDTO.getId() == null
				&& newArtistDTO.getDob().isBefore(LocalDate.now())
		){
			
			Artist createdArtist = artistService.createArtist(new Artist(newArtistDTO));
	        return new ResponseEntity<>(createdArtist, HttpStatus.CREATED);	
		}
        return new ResponseEntity<>("Artist has invlaid values", HttpStatus.BAD_REQUEST);	

	}
	
	@PutMapping("/artists")
	public ResponseEntity<?> updatedArtists(@RequestBody ArtistDTO updatedArtistDTO) {
		AllArtists = artistService.getAllArtists();
		if (verificationUtil.stringHasValue(updatedArtistDTO.getEmail()) 
				&& verificationUtil.stringHasValue(updatedArtistDTO.getFirstName())
				&& verificationUtil.stringHasValue(updatedArtistDTO.getLastName())
				&& updatedArtistDTO.getId() != null
				&& updatedArtistDTO.getDob().isBefore(LocalDate.now())
		) {
			Artist updatedArtist = artistService.updateArtist(new Artist(updatedArtistDTO));
	        return new ResponseEntity<>(updatedArtist, HttpStatus.CREATED);	
		}
        return new ResponseEntity<>("Artist has invlaid values", HttpStatus.BAD_REQUEST);	
	}



	
}
