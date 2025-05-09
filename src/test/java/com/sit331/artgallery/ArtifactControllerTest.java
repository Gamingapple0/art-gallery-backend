package com.sit331.artgallery;

import com.sit331.artgallery.controllers.ArtifactController;
import com.sit331.artgallery.dto.ArtifactDTO;
import com.sit331.artgallery.dto.ArtistBasicDTO;
import com.sit331.artgallery.dto.BidNoArtifactDTO;
import com.sit331.artgallery.entities.Artifact;
import com.sit331.artgallery.entities.Artist;
import com.sit331.artgallery.entities.ArtType;
import com.sit331.artgallery.entities.Bid;
import com.sit331.artgallery.services.ArtifactService;
import com.sit331.artgallery.services.BidService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ArtifactControllerTest {

    @InjectMocks
    private ArtifactController artifactController;

    @Mock
    private ArtifactService artifactService;

    @Mock
    private BidService bidService;

    private ArtifactDTO validArtifactDTO;
    private Artifact validArtifact;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<ArtType> artTypes = List.of(new ArtType(null,"Painting","New","New","New","New"));
        List<Artifact> artifacts = artifactService.getAllArtifacts().stream().map((artiDTO)-> new Artifact(artiDTO)).toList();
        ArtistBasicDTO artist = new ArtistBasicDTO(new Artist(null,"New","New",LocalDate.now(),"New","New",false,artifacts,"New"));
        artist.setId(1);

        validArtifactDTO = new ArtifactDTO();
        validArtifactDTO.setName("Sunset");
        validArtifactDTO.setPrice(500f);
        validArtifactDTO.setArtist(artist);
        validArtifactDTO.setArtTypes(artTypes);
        validArtifactDTO.setEndDate(LocalDate.now());

        validArtifact = new Artifact(validArtifactDTO);
    }

    @Test
    void testCreateArtifact_ValidData_ReturnsCreated() {
        when(artifactService.getAllArtifacts()).thenReturn(List.of(validArtifactDTO));
        when(artifactService.createArtifact(validArtifactDTO)).thenReturn(validArtifact);

        ResponseEntity<?> response = artifactController.createArtifact(validArtifactDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(validArtifact, response.getBody());
    }

    @Test
    void testCreateArtifact_InvalidEndDate_ReturnsBadRequest() {
        validArtifactDTO.setEndDate(LocalDate.now().minusDays(1));

        ResponseEntity<?> response = artifactController.createArtifact(validArtifactDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testUpdateArtifact_ValidData_ReturnsCreated() {
        when(artifactService.getAllArtifacts()).thenReturn(List.of(validArtifactDTO));
        when(artifactService.updateArtifact(validArtifact)).thenReturn(validArtifact);

        ResponseEntity<?> response = artifactController.updateArtifact(validArtifact);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(validArtifact, response.getBody());
    }

    @Test
    void testUpdateArtifact_MissingName_ReturnsBadRequest() {
        validArtifact.setName(null);

        ResponseEntity<?> response = artifactController.updateArtifact(validArtifact);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testAddBidToArtifact_ReturnsOkWithDTO() {
        int artifactId = 1;
        Bid bid = new Bid();
        bid.setNewBid(200);
        bid.setTimestamp(LocalDate.now().atStartOfDay());

        when(artifactService.getArtifactById(artifactId)).thenReturn(validArtifact);
        when(bidService.createBid(bid)).thenReturn(bid);

        ResponseEntity<?> response = artifactController.addBid(bid, artifactId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(BidNoArtifactDTO.class, response.getBody());
    }

    @Test
    void testDeleteArtifact_Success_ReturnsNoContent() {
        int id = 1;
        when(artifactService.getAllArtifacts()).thenReturn(List.of(validArtifactDTO));
        when(artifactService.deleteArtifact(id)).thenReturn(true);

        ResponseEntity<String> response = artifactController.deleteArtifact(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteArtifact_NotFound_ReturnsNotFound() {
        int id = 100;
        when(artifactService.getAllArtifacts()).thenReturn(List.of(validArtifactDTO));
        when(artifactService.deleteArtifact(id)).thenReturn(false);

        ResponseEntity<String> response = artifactController.deleteArtifact(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetArtifacts_ReturnsList() {
        when(artifactService.getAllArtifacts()).thenReturn(List.of(validArtifactDTO));

        List<ArtifactDTO> result = artifactController.getArtifacts();

        assertEquals(1, result.size());
    }

    @Test
    void testGetArtifactByIndex_ReturnsCorrectArtifact() {
        List<ArtifactDTO> mockList = new ArrayList<>();
        mockList.add(validArtifactDTO);
        mockList.add(new ArtifactDTO()); 

        when(artifactService.getAllArtifacts()).thenReturn(mockList);

        artifactController.getArtifacts();
        ArtifactDTO result = artifactController.getArtifact(0);

        assertEquals(validArtifactDTO, result);
    }
}
