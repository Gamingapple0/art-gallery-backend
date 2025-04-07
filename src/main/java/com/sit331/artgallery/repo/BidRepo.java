package com.sit331.artgallery.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sit331.artgallery.entities.Bid;

@Repository
public interface BidRepo extends JpaRepository<Bid,Integer> {

}
