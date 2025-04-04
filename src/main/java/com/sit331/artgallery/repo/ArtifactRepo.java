package com.sit331.artgallery.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sit331.artgallery.entities.Artifact;

@Repository
public interface ArtifactRepo extends JpaRepository<Artifact, Integer> {

}
