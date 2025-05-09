package com.sit331.artgallery;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sit331.artgallery.controllers.ArtistController;
import com.sit331.artgallery.dto.ArtistDTO;
import com.sit331.artgallery.entities.Artist;
import com.sit331.artgallery.services.ArtistService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ArtistControllerTest {

    @Autowired
    private ArtistController artistController;

    @MockBean
    private ArtistService artistService;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate dob = LocalDate.of(1990, 1, 1);

    @Test
    void testGetAllArtists() {
        Artist artist = new Artist();
        artist.setId(1);
        artist.setFirstName("Emily");
        artist.setLastName("Smith");

        List<Artist> artists = Arrays.asList(artist);
        when(artistService.getAllArtists()).thenReturn(artists);

        ResponseEntity<?> response = artistController.getAllArtists();
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<?> body = (List<?>) response.getBody();
        assertEquals("Emily", ((Artist) body.get(0)).getFirstName());
    }

    @Test
    void testCreateArtist_Success() {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setFirstName("John");
        artistDTO.setLastName("Doe");
        artistDTO.setEmail("john@example.com");
        artistDTO.setDob(dob);
        artistDTO.setMale(true);

        Artist savedArtist = new Artist(artistDTO);
        savedArtist.setId(1);

        when(artistService.getAllArtists()).thenReturn(Collections.emptyList());
        when(artistService.createArtist(any(Artist.class))).thenReturn(savedArtist);

        ResponseEntity<?> response = artistController.createArtist(artistDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, ((Artist) response.getBody()).getId());
    }

    @Test
    void testCreateArtist_Invalid() {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setFirstName("John");
        artistDTO.setLastName("");
        artistDTO.setEmail("invalidemail");
        artistDTO.setDob(dob);

        when(artistService.getAllArtists()).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = artistController.createArtist(artistDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testUpdateArtist_Success() {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setId(1);
        artistDTO.setFirstName("Jane");
        artistDTO.setLastName("Doe");
        artistDTO.setEmail("jane@example.com");
        artistDTO.setDob(dob);
        artistDTO.setMale(false);

        Artist updatedArtist = new Artist(artistDTO);

        when(artistService.getAllArtists()).thenReturn(Collections.emptyList());
        when(artistService.updateArtist(any(Artist.class))).thenReturn(updatedArtist);

        ResponseEntity<?> response = artistController.updatedArtists(artistDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Jane", ((Artist) response.getBody()).getFirstName());
    }

    @Test
    void testUpdateArtist_Invalid() {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setId(null);
        artistDTO.setFirstName("Jane");
        artistDTO.setLastName("Doe");
        artistDTO.setEmail("jane@example.com");
        artistDTO.setDob(null);

        when(artistService.getAllArtists()).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = artistController.updatedArtists(artistDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testDeleteArtist_Success() {
        int artistId = 1;

        when(artistService.getAllArtists()).thenReturn(Collections.emptyList());
        when(artistService.deleteArtist(artistId)).thenReturn(true);

        ResponseEntity<?> response = artistController.deleteArtists(artistId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteArtist_NotFound() {
        int artistId = 999;

        when(artistService.getAllArtists()).thenReturn(Collections.emptyList());
        when(artistService.deleteArtist(artistId)).thenReturn(false);

        ResponseEntity<?> response = artistController.deleteArtists(artistId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
