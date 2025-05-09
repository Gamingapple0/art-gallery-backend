package com.sit331.artgallery;

import com.sit331.artgallery.dto.ArtistBasicDTO;
import com.sit331.artgallery.dto.ArtistDTO;
import com.sit331.artgallery.entities.Artifact;
import com.sit331.artgallery.entities.Artist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArtistTest {

    @Test
    void testEmptyConstructor() {
        Artist artist = new Artist();
        assertNotNull(artist);
    }

    @Test
    void testConstructorWithFields() {
        List<Artifact> artWorks = new ArrayList<>();

        LocalDate dob = LocalDate.of(1990, 5, 10);
        Artist artist = new Artist(1, "John", "Doe", dob, "john.doe@example.com",
                "An amazing artist", true, artWorks, "image.jpg");

        assertEquals(1, artist.getId());
        assertEquals("John", artist.getFirstName());
        assertEquals("Doe", artist.getLastName());
        assertEquals(dob, artist.getDOB());
        assertEquals("john.doe@example.com", artist.getEmail());
        assertEquals("An amazing artist", artist.getBio());
        assertTrue(artist.isMale());
        assertEquals(artWorks, artist.getArtWorks());
        assertEquals("image.jpg", artist.getImgURL());
    }

    @Test
    void testConstructorWithArtistDTO() {
        List<Artifact> artWorks = new ArrayList<>();

        ArtistDTO dto = new ArtistDTO();
        dto.setId(2);
        dto.setFirstName("Jane");
        dto.setLastName("Smith");
        dto.setDob(LocalDate.of(1985, 3, 15));
        dto.setEmail("jane.smith@example.com");
        dto.setBio("Famous for abstract art");
        dto.setMale(false);
        dto.setArtistArtifacts(artWorks);
        dto.setImgURL("jane.jpg");

        Artist artist = new Artist(dto);

        assertEquals(2, artist.getId());
        assertEquals("Jane", artist.getFirstName());
        assertEquals("Smith", artist.getLastName());
        assertEquals(dto.getDob(), artist.getDOB());
        assertEquals("jane.smith@example.com", artist.getEmail());
        assertEquals("Famous for abstract art", artist.getBio());
        assertFalse(artist.isMale());
        assertEquals(artWorks, artist.getArtWorks());
        assertEquals("jane.jpg", artist.getImgURL());
    }

    @Test
    void testConstructorWithArtistBasicDTO() {
        ArtistBasicDTO dto = new ArtistBasicDTO();
        dto.setId(3);
        dto.setFirstName("Alex");
        dto.setLastName("Brown");
        dto.setDob(LocalDate.of(1970, 12, 25));
        dto.setEmail("alex.brown@example.com");
        dto.setBio("Portrait expert");
        dto.setMale(true);
        dto.setImgURL("alex.png");

        Artist artist = new Artist(dto);

        assertEquals(3, artist.getId());
        assertEquals("Alex", artist.getFirstName());
        assertEquals("Brown", artist.getLastName());
        assertEquals(dto.getDob(), artist.getDOB());
        assertEquals("alex.brown@example.com", artist.getEmail());
        assertEquals("Portrait expert", artist.getBio());
        assertTrue(artist.isMale());
        assertNull(artist.getArtWorks());
        assertEquals("alex.png", artist.getImgURL());
    }

    @Test
    void testSettersAndGetters() {
        Artist artist = new Artist();
        artist.setId(4);
        artist.setFirstName("Emily");
        artist.setLastName("Green");
        artist.setDOB(LocalDate.of(2000, 1, 1));
        artist.setEmail("emily.green@example.com");
        artist.setBio("Modernist");
        artist.setMale(false);
        List<Artifact> artWorks = new ArrayList<>();
        artist.setArtWorks(artWorks);
        artist.setImgURL("emily.jpg");

        assertEquals(4, artist.getId());
        assertEquals("Emily", artist.getFirstName());
        assertEquals("Green", artist.getLastName());
        assertEquals(LocalDate.of(2000, 1, 1), artist.getDOB());
        assertEquals("emily.green@example.com", artist.getEmail());
        assertEquals("Modernist", artist.getBio());
        assertFalse(artist.isMale());
        assertEquals(artWorks, artist.getArtWorks());
        assertEquals("emily.jpg", artist.getImgURL());
    }
}
