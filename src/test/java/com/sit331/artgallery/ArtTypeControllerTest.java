package com.sit331.artgallery;

import com.sit331.artgallery.controllers.ArtTypesController;
import com.sit331.artgallery.entities.ArtType;
import com.sit331.artgallery.services.ArtTypeService;
import com.sit331.artgallery.util.verificationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class ArtTypesControllerTest {

    @Mock
    private ArtTypeService artTypeService;

    @InjectMocks
    private ArtTypesController artTypesController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetArtTypes() {
        List<ArtType> mockList = List.of(
                new ArtType(1, "Style 1", "Desc", "Method", "Group", "Region"),
                new ArtType(2, "Style 2", "Desc", "Method", "Group", "Region")
        );
        when(artTypeService.getAllArtTypes()).thenReturn(mockList);

        List<ArtType> result = artTypesController.getArtTypes();
        assertEquals(2, result.size());
    }

    @Test
    void testCreateArtType_Valid() {
        ArtType input = new ArtType(null, "Style", "Desc", "Method", "Group", "Region");
        ArtType created = new ArtType(1, "Style", "Desc", "Method", "Group", "Region");

        when(artTypeService.getAllArtTypes()).thenReturn(List.of());
        when(artTypeService.createArtType(input)).thenReturn(created);

        ResponseEntity<?> response = artTypesController.createArtType(input);
        assertEquals(201, response.getStatusCodeValue());
        verify(artTypeService).createArtType(input);
    }

    @Test
    void testCreateArtType_Invalid() {
        ArtType invalidInput = new ArtType();
        invalidInput.setName("");
        invalidInput.setCulturalGroup("");
        invalidInput.setRegion("");
        invalidInput.setCreationMethod("");

        ResponseEntity<?> response = artTypesController.createArtType(invalidInput);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Body and Title should not be empty", response.getBody());
    }

    @Test
    void testUpdateArtType_Valid() {
        ArtType updated = new ArtType(1, "Updated", "Updated", "Method", "Group", "Region");

        when(artTypeService.getAllArtTypes()).thenReturn(List.of(updated));
        when(artTypeService.updateArtType(updated)).thenReturn(updated);

        ResponseEntity<?> response = artTypesController.updatedArtType(updated);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void testUpdateArtType_Invalid() {
        ArtType invalid = new ArtType();
        invalid.setName("");
        invalid.setCulturalGroup("");
        invalid.setRegion("");
        invalid.setCreationMethod("");

        ResponseEntity<?> response = artTypesController.updatedArtType(invalid);
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void testDeleteArtType_Success() {
        when(artTypeService.getAllArtTypes()).thenReturn(List.of());
        when(artTypeService.deleteArtType(1)).thenReturn(true);

        ResponseEntity<String> response = artTypesController.deleteArtType(1);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testDeleteArtType_NotFound() {
        when(artTypeService.getAllArtTypes()).thenReturn(List.of());
        when(artTypeService.deleteArtType(999)).thenReturn(false);

        ResponseEntity<String> response = artTypesController.deleteArtType(999);
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Art Type Not Found", response.getBody());
    }
}
