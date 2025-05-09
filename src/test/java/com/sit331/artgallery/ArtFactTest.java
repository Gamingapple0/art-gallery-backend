package com.sit331.artgallery;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sit331.artgallery.entities.ArtFact;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArtFactTest {

    @Test
    void testArtFactConstructorAndGetters() {
        ArtFact artFact = new ArtFact(1, "Cultural Symbolism", "This painting represents heritage.");

        assertEquals(1, artFact.getId());
        assertEquals("Cultural Symbolism", artFact.getFactTitle());
        assertEquals("This painting represents heritage.", artFact.getFactBody());
    }

    @Test
    void testSetters() {
        ArtFact artFact = new ArtFact();
        artFact.setFactTitle("New Title");
        artFact.setFactBody("New Body");

        assertEquals("New Title", artFact.getFactTitle());
        assertEquals("New Body", artFact.getFactBody());
    }
}
