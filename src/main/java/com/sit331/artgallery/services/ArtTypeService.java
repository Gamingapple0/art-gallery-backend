package com.sit331.artgallery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sit331.artgallery.entities.ArtType;
import com.sit331.artgallery.repo.ArtTypeRepo;

@Service
public class ArtTypeService {
	@Autowired
	private ArtTypeRepo repo; 
	
	public List<ArtType> getAllArtTypes() {
		return repo.findAll();		
	}
	
	public ArtType createArtType(ArtType newArtType) {
		return repo.save(newArtType);
	}
	
	public ArtType updateArtType(ArtType updatedArtType) {
		return repo.save(updatedArtType);
	}
	
	public boolean deleteArtType(int id) {
		if (repo.findById(id).isEmpty()) {return false;}
		repo.deleteById(id);
		return true;
	}
}
