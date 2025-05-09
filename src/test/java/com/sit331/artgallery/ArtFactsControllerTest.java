package com.sit331.artgallery;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.sit331.artgallery.controllers.ArtFactsController;
import com.sit331.artgallery.entities.ArtFact;
import com.sit331.artgallery.services.ArtFactService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArtFactsControllerTest {

    @Mock
    private ArtFactService artFactService;

    @InjectMocks
    private ArtFactsController artFactsController;

    public ArtFactsControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetArtFacts() {
        List<ArtFact> mockFacts = Arrays.asList(
                new ArtFact(1, "Title 1", "Body 1"),
                new ArtFact(2, "Title 2", "Body 2")
        );

        when(artFactService.getAllArtFacts()).thenReturn(mockFacts);
        List<ArtFact> result = artFactsController.getArtFacts();

        assertEquals(2, result.size());
    }

    @Test
    void testCreateArtFact_ValidInput() {
        ArtFact artFact = new ArtFact(null, "Interesting Fact", "Detailed explanation.");
        when(artFactService.getAllArtFacts()).thenReturn(List.of());
        when(artFactService.createArtFact(any())).thenReturn(artFact);

        ResponseEntity<?> response = artFactsController.createArtFact(artFact);

        assertEquals(201, response.getStatusCodeValue());
        verify(artFactService).createArtFact(artFact);
    }

    @Test
    void testCreateArtFact_InvalidInput() {
        ArtFact artFact0 = new ArtFact(null, "Valid", "");
        ArtFact artFact1 = new ArtFact(null, "", "Valid");
        ArtFact artFact2 = new ArtFact(null, "Valid", null);
        ArtFact artFact3 = new ArtFact(null, null, "Valid");

        ResponseEntity<?> response0 = artFactsController.createArtFact(artFact0);
        ResponseEntity<?> response1 = artFactsController.createArtFact(artFact1);
        ResponseEntity<?> response2 = artFactsController.createArtFact(artFact2);
        ResponseEntity<?> response3 = artFactsController.createArtFact(artFact3);

        assertEquals(400, response0.getStatusCodeValue());
        assertEquals(400, response1.getStatusCodeValue());
        assertEquals(400, response2.getStatusCodeValue());
        assertEquals(400, response3.getStatusCodeValue());
        assertEquals("Body and Title should not be empty", response0.getBody());
        assertEquals("Body and Title should not be empty", response1.getBody());
        assertEquals("Body and Title should not be empty", response2.getBody());
        assertEquals("Body and Title should not be empty", response3.getBody());
        
    }

    @Test
    void testUpdateArtFact_ValidInput() {
        ArtFact artFact = new ArtFact(1, "Updated Title", "Updated Body");

        when(artFactService.getAllArtFacts()).thenReturn(List.of(artFact));
        when(artFactService.updateArtFact(any())).thenReturn(artFact);

        ResponseEntity<?> response = artFactsController.updatedArtFact(artFact);

        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void testDeleteArtFact_Success() {
        when(artFactService.getAllArtFacts()).thenReturn(List.of());
        when(artFactService.deleteArtFact(1)).thenReturn(true);

        ResponseEntity<String> response = artFactsController.deleteArtFact(1);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testDeleteArtFact_NotFound() {
        when(artFactService.getAllArtFacts()).thenReturn(List.of());
        when(artFactService.deleteArtFact(1)).thenReturn(false);

        ResponseEntity<String> response = artFactsController.deleteArtFact(1);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Art Fact does not exist", response.getBody());
    }
}