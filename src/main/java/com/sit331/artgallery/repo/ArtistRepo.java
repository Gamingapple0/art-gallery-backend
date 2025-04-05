package com.sit331.artgallery.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sit331.artgallery.entities.Artist;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Integer>{
	public boolean existsByEmail(String Email);
}
