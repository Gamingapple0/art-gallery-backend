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

import com.sit331.artgallery.entities.ArtType;
import com.sit331.artgallery.services.ArtTypeService;
import com.sit331.artgallery.util.verificationUtil;

@RestController
public class ArtTypesController {
	@Autowired
	private ArtTypeService artTypeService;
	
	private List<ArtType> AllArtTypes; 
	
	@GetMapping("/art-types")
	public List<ArtType> getArtTypes() {
		AllArtTypes = artTypeService.getAllArtTypes();
		return AllArtTypes;
	}
	
	@PostMapping("/art-types")
	public ResponseEntity<?> createArtType(@RequestBody ArtType newArtType) {
		AllArtTypes = artTypeService.getAllArtTypes();
		if (verificationUtil.stringHasValue(newArtType.getName()) 
				&& verificationUtil.stringHasValue(newArtType.getCulturalGroup()) 
				&& verificationUtil.stringHasValue(newArtType.getRegion()) 
				&& verificationUtil.stringHasValue(newArtType.getCreationMethod())) {
			ArtType createdArtType = artTypeService.createArtType(newArtType);
	        return new ResponseEntity<>(createdArtType, HttpStatus.CREATED);	
		}
        return new ResponseEntity<>("Body and Title should not be empty", HttpStatus.BAD_REQUEST);	
	}
	
	
	@PutMapping("/art-types")
	public ResponseEntity<?> updatedArtType(@RequestBody ArtType updatedArtType) {
		AllArtTypes = artTypeService.getAllArtTypes();
		if (verificationUtil.stringHasValue(updatedArtType.getName()) 
				&& verificationUtil.stringHasValue(updatedArtType.getCulturalGroup()) 
				&& verificationUtil.stringHasValue(updatedArtType.getRegion()) 
				&& verificationUtil.stringHasValue(updatedArtType.getCreationMethod()))
		{
			ArtType updatedArtType1 = artTypeService.updateArtType(updatedArtType);
	        return new ResponseEntity<>(updatedArtType1, HttpStatus.CREATED);	
		}
        return new ResponseEntity<>("Body and Title should not be empty", HttpStatus.BAD_REQUEST);	
	}
	
	@DeleteMapping("/art-types/{id}")
	public ResponseEntity<String> deleteArtType(@PathVariable("id") int id) {
		AllArtTypes = artTypeService.getAllArtTypes();
		if (artTypeService.deleteArtType(id)) {
			return new ResponseEntity<String>("Successfully deleted", HttpStatus.NO_CONTENT);	
		}
		return new ResponseEntity<String>("Art Type Not Found", HttpStatus.NOT_FOUND);
	}
}
