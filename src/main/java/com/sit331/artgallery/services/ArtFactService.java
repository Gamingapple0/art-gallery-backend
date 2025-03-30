package com.sit331.artgallery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sit331.artgallery.entities.ArtFact;
import com.sit331.artgallery.repo.ArtFactRepo;

@Service
public class ArtFactService {

	@Autowired
	private ArtFactRepo repo; 
	
	public List<ArtFact> getAllArtFacts() {
		return repo.findAll();		
	}
	
	public ArtFact createArtFact(ArtFact newArtFact) {
		return repo.save(newArtFact);
	}
	
	public ArtFact updateArtFact(ArtFact updatedArtFact) {
		return repo.save(updatedArtFact);
	}
	
	public boolean deleteArtFact(int id) {
		if (repo.findById(id).isEmpty()) {return false;}
		repo.deleteById(id);
		return true;
		
	}
}
