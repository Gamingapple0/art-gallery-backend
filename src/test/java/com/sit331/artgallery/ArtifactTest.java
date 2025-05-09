package com.sit331.artgallery;

import com.sit331.artgallery.dto.ArtifactDTO;
import com.sit331.artgallery.dto.ArtistBasicDTO;
import com.sit331.artgallery.entities.ArtType;
import com.sit331.artgallery.entities.Artifact;
import com.sit331.artgallery.entities.Artist;
import com.sit331.artgallery.entities.Bid;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtifactTest {


    @Test
    void testConstructorWithFields() {
        Artist artist = new Artist();
        List<ArtType> artTypes = new ArrayList<ArtType>();
        LocalDate now = LocalDate.now();
        List<Bid> bids = new ArrayList<Bid>();
        Artifact artifact = new Artifact(
                new Integer(1),
                "Dot Painting",
                new Float(300),
                "dot_painting.jpg",
                artTypes,
                artist,
                "Traditional Aboriginal dot painting on canvas",
                now,
                bids
        );

        assertEquals(1, artifact.getId());
        assertEquals("Dot Painting", artifact.getName());
        assertEquals(new Float(300.0), artifact.getPrice());
        assertEquals("Traditional Aboriginal dot painting on canvas", artifact.getDescription());
        assertEquals("dot_painting.jpg", artifact.getImgURL());
        assertEquals(now, artifact.getEndDate());
    }

    @Test
    void testConstructorWithArtifactDTO() {
        ArtifactDTO dto = new ArtifactDTO();
        List<Artifact> artWorks = new ArrayList<>();

        LocalDate dob = LocalDate.of(1990, 5, 10);
        Artist artist = new Artist(1, "John", "Doe", dob, "john.doe@example.com",
                "An amazing artist", true, artWorks, "image.jpg");
        
        dto.setId(2);
        dto.setName("Bark Art");
        dto.setPrice(new Float(300));
        dto.setDescription("Artwork on tree bark");
        dto.setImgURL("bark_art.jpg");
        LocalDate created = LocalDate.of(2024, 4, 10);
        dto.setEndDate(created);
        dto.setArtist(new ArtistBasicDTO(artist));

        Artifact artifact = new Artifact(dto);

        assertEquals("Bark Art", artifact.getName());
        assertEquals("Artwork on tree bark", artifact.getDescription());
        assertEquals("bark_art.jpg", artifact.getImgURL());
        assertEquals(created, artifact.getEndDate());
    }

    @Test
    void testSettersAndGetters() {
        Artifact artifact = new Artifact();

        Artist artist = new Artist();
        ArtType artType = new ArtType();
        LocalDate now = LocalDate.now();

        artifact.setName("Cave Etching");
        artifact.setPrice(new Float(1000.0));
        artifact.setDescription("Ancient etching discovered in sacred cave");
        artifact.setImgURL("etching.jpg");
        artifact.setEndDate(now);        
        artifact.setArtist(artist);

        assertEquals("Cave Etching", artifact.getName());
        assertEquals(new Float(1000.0), artifact.getPrice());
        assertEquals("Ancient etching discovered in sacred cave", artifact.getDescription());
        assertEquals("etching.jpg", artifact.getImgURL());
        assertEquals(now, artifact.getEndDate());
    }
}
