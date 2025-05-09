package com.sit331.artgallery;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sit331.artgallery.entities.ArtType;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ArtTypeTest {

    @Test
    void testConstructorAndGetters() {
        ArtType artType = new ArtType(1, "Dot Painting", "Indigenous technique", "Hand Painted", "Yolŋu", "Northern Territory");

        assertEquals(1, artType.getId());
        assertEquals("Dot Painting", artType.getName());
        assertEquals("Indigenous technique", artType.getDescription());
        assertEquals("Hand Painted", artType.getCreationMethod());
        assertEquals("Yolŋu", artType.getCulturalGroup());
        assertEquals("Northern Territory", artType.getRegion());
    }

    @Test
    void testSetters() {
        ArtType artType = new ArtType();
        artType.setId(2);
        artType.setName("Rock Art");
        artType.setDescription("Ancient rock painting");
        artType.setCreationMethod("Natural Pigments");
        artType.setCulturalGroup("Warlpiri");
        artType.setRegion("Central Australia");

        assertEquals(2, artType.getId());
        assertEquals("Rock Art", artType.getName());
        assertEquals("Ancient rock painting", artType.getDescription());
        assertEquals("Natural Pigments", artType.getCreationMethod());
        assertEquals("Warlpiri", artType.getCulturalGroup());
        assertEquals("Central Australia", artType.getRegion());
    }
}
