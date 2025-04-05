package com.sit331.artgallery.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sit331.artgallery.dto.ArtifactDTO;
import com.sit331.artgallery.dto.ArtistBasicDTO;
import com.sit331.artgallery.entities.ArtType;
import com.sit331.artgallery.entities.Artifact;
import com.sit331.artgallery.entities.Artist;
import com.sit331.artgallery.repo.ArtTypeRepo;
import com.sit331.artgallery.repo.ArtifactRepo;
import com.sit331.artgallery.repo.ArtistRepo;

import jakarta.validation.Valid;

@Service
public class ArtifactService {
	@Autowired
	private ArtifactRepo repo;
	@Autowired
	private ArtistRepo artistRepo;
	@Autowired
	private ArtTypeRepo artTypeRepo; 
	
	public List<ArtifactDTO> getAllArtifacts() {
		return repo.findAll().stream().map(ArtifactDTO::new).toList();
	}
	
	// Can't create artifact without existing artist and art types
	public Artifact createArtifact(@Valid ArtifactDTO newArtifactDTO) {
		List<ArtType> artTypes= newArtifactDTO.getArtTypes();
		List<ArtType> actualArtTypes = new ArrayList<ArtType>();
		for(int i = 0; i < artTypes.size(); i++) {
			Optional<ArtType> artTypeOptional = artTypeRepo.findById(artTypes.get(i).getId());
			if (artTypeOptional.isPresent()) {
			    actualArtTypes.add(artTypeOptional.get());
			}
			else {
				throw new NoSuchElementException("Art Type not found with ID: " + artTypes.get(i).getId());
			}
		}
		Optional<Artist> artistOptional =artistRepo.findById(newArtifactDTO.getArtist().getId());
		if (!artistOptional.isPresent()) {
			throw new NoSuchElementException("Artist not found with ID: " + newArtifactDTO.getArtist().getId());
		}
		ArtistBasicDTO newArtistDTO = new ArtistBasicDTO(artistOptional.get());
		newArtifactDTO.setArtist(newArtistDTO);
		newArtifactDTO.setArtTypes(actualArtTypes);
		Artifact newArtifact = new Artifact(newArtifactDTO);
		return repo.save(newArtifact);
	}
	
	public Artifact updateArtifact(Artifact updatedArtifact) {
		// Can't create artifact without existing artist and art types
		if (!artistRepo.existsById(updatedArtifact.getArtist().getId())) {
			throw new NoSuchElementException("Artist not found with ID: " + updatedArtifact.getArtist().getId());
		}
		List<ArtType> artTypes= updatedArtifact.getArtTypes();
		for(int i = 0; i < artTypes.size(); i++) {
			if (!artTypeRepo.existsById(artTypes.get(i).getId())) {
				throw new NoSuchElementException("Art Type not found with ID: " + artTypes.get(i).getId());
			}
		}
		return repo.save(updatedArtifact);
	}
	
	public boolean deleteArtifact(int id) {
		if (repo.findById(id).isEmpty()) {return false;}
		repo.deleteById(id);
		return true;
		
	}
}
