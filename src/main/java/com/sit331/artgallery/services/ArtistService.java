package com.sit331.artgallery.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sit331.artgallery.entities.Artist;
import com.sit331.artgallery.entities.Artifact;
import com.sit331.artgallery.repo.ArtifactRepo;
import com.sit331.artgallery.repo.ArtistRepo;

@Service
public class ArtistService {
	@Autowired
	private ArtistRepo repo;
	
	@Autowired
	private ArtifactRepo artifactRepo;
	
	public List<Artist> getAllArtists() {
		return repo.findAll();		
	}
	
	public Artist createArtist(Artist newArtist) {
		boolean emailExists = repo.existsByEmail(newArtist.getEmail());
		if (emailExists) {
            throw new IllegalArgumentException("The email " + newArtist.getEmail() + " already exists.");
		}
//		newArtist.setArtWorks(new ArrayList<Artifact>());
		return repo.save(newArtist);
	}
	
	public Artist updateArtist(Artist updatedArtist) {
		boolean emailExists = repo.existsByEmail(updatedArtist.getEmail());
		Optional<Artist> existingArtist = repo.findById(updatedArtist.getId());
		if(!existingArtist.isPresent()) {
			throw new NoSuchElementException("Artist not found with ID: " + updatedArtist.getId());
		}
		if (emailExists && !(existingArtist.get().getEmail()).equals(updatedArtist.getEmail())) {
            throw new IllegalArgumentException("The email " + updatedArtist.getEmail() + " already exists.");
		}
		List<Artifact> updatedArtifacts = updatedArtist.getArtWorks();
		if (updatedArtifacts != null) {
			for (int i = 0; i < updatedArtifacts.size(); i++) {
				boolean artifactExists = artifactRepo.existsById(updatedArtifacts.get(i).getId());
				if(!artifactExists) {
				    throw new NoSuchElementException("Artist not found with ID: " + updatedArtifacts.get(i).getId());
				}
			}	
		}
		
		return repo.save(updatedArtist);
	}
	
	public boolean deleteArtifact(int id) {
		if (repo.findById(id).isEmpty()) {return false;}
		repo.deleteById(id);
		return true;
	}
}
