package fr.esgi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JeuTest {

    private Editeur editeur;
    private Genre genre;
    private Classification classification;
    private LocalDate dateSortie;
    private List<Plateforme> plateformes;

    @BeforeEach
    void setUp() {
        // Initialize test data
        editeur = new Editeur();
        genre = new Genre();
        classification = new Classification();
        dateSortie = LocalDate.of(2023, 5, 10);
        plateformes = new ArrayList<>();
        plateformes.add(new Plateforme());
    }

    @Test
    void testNoArgsConstructor() {
        // Test the no-args constructor from Lombok @NoArgsConstructor
        Jeu jeu = new Jeu();
        assertNotNull(jeu);
        assertNull(jeu.getNom()); // Since @NonNull is not enforced at runtime by Lombok
    }

    @Test
    void testAllArgsConstructor() {
        // Test the all-args constructor from Lombok @AllArgsConstructor
        Jeu jeu = new Jeu(1L, "Test Game", editeur, genre, classification,
                "A test game", dateSortie, plateformes, "image.jpg", 59.99f);

        assertEquals(1L, jeu.getId());
        assertEquals("Test Game", jeu.getNom());
        assertEquals(editeur, jeu.getEditeur());
        assertEquals(genre, jeu.getGenre());
        assertEquals(classification, jeu.getClassification());
        assertEquals("A test game", jeu.getDescription());
        assertEquals(dateSortie, jeu.getDateDeSortie());
        assertEquals(plateformes, jeu.getPlateformes());
        assertEquals("image.jpg", jeu.getImage());
        assertEquals(59.99f, jeu.getPrix(), 0.001);
    }

    @Test
    void testTwoArgsConstructor() {
        // Test the custom constructor with name and publisher
        Jeu jeu = new Jeu("Test Game", editeur);

        assertEquals("Test Game", jeu.getNom());
        assertEquals(editeur, jeu.getEditeur());
        assertNull(jeu.getGenre());
        assertNull(jeu.getClassification());
        assertNull(jeu.getDescription());
        assertNull(jeu.getDateDeSortie());
        assertNull(jeu.getPlateformes());
        assertNull(jeu.getImage());
        assertEquals(0.0f, jeu.getPrix(), 0.001);
    }

    @Test
    void testThreeArgsConstructor() {
        // Test the custom constructor with name, release date, and publisher
        Jeu jeu = new Jeu("Test Game", dateSortie, editeur);

        assertEquals("Test Game", jeu.getNom());
        assertEquals(editeur, jeu.getEditeur());
        assertEquals(dateSortie, jeu.getDateDeSortie());
        assertNull(jeu.getGenre());
        assertNull(jeu.getClassification());
        assertNull(jeu.getDescription());
        assertNull(jeu.getPlateformes());
        assertNull(jeu.getImage());
        assertEquals(0.0f, jeu.getPrix(), 0.001);
    }

    @Test
    void testFourArgsConstructor() {
        // Test the custom constructor with name, description, release date, and publisher
        Jeu jeu = new Jeu("Test Game", "A test game", dateSortie, editeur);

        assertEquals("Test Game", jeu.getNom());
        assertEquals("A test game", jeu.getDescription());
        assertEquals(dateSortie, jeu.getDateDeSortie());
        assertEquals(editeur, jeu.getEditeur());
        assertNull(jeu.getGenre());
        assertNull(jeu.getClassification());
        assertNull(jeu.getPlateformes());
        assertNull(jeu.getImage());
        assertEquals(0.0f, jeu.getPrix(), 0.001);
    }

    @Test
    void testFiveArgsConstructor() {
        // Test the custom constructor with name, release date, publisher, and genre
        Jeu jeu = new Jeu("Test Game", dateSortie, editeur, genre);

        assertEquals("Test Game", jeu.getNom());
        assertEquals(dateSortie, jeu.getDateDeSortie());
        assertEquals(editeur, jeu.getEditeur());
        assertEquals(genre, jeu.getGenre());
        assertNull(jeu.getDescription()); // Description is null despite passing null in the chain
        assertNull(jeu.getClassification());
        assertNull(jeu.getPlateformes());
        assertNull(jeu.getImage());
        assertEquals(0.0f, jeu.getPrix(), 0.001);
    }

    @Test
    void testBuilderPattern() {
        // Test the builder pattern from Lombok @Builder
        Jeu jeu = Jeu.builder()
                .id(1L)
                .nom("Test Game")
                .editeur(editeur)
                .genre(genre)
                .classification(classification)
                .description("A test game")
                .dateDeSortie(dateSortie)
                .plateformes(plateformes)
                .image("image.jpg")
                .prix(59.99f)
                .build();

        assertEquals(1L, jeu.getId());
        assertEquals("Test Game", jeu.getNom());
        assertEquals(editeur, jeu.getEditeur());
        assertEquals(genre, jeu.getGenre());
        assertEquals(classification, jeu.getClassification());
        assertEquals("A test game", jeu.getDescription());
        assertEquals(dateSortie, jeu.getDateDeSortie());
        assertEquals(plateformes, jeu.getPlateformes());
        assertEquals("image.jpg", jeu.getImage());
        assertEquals(59.99f, jeu.getPrix(), 0.001);
    }

    @Test
    void testSettersAndGetters() {
        // Test the setters and getters from Lombok @Data
        Jeu jeu = new Jeu();

        jeu.setId(1L);
        jeu.setNom("Test Game");
        jeu.setEditeur(editeur);
        jeu.setGenre(genre);
        jeu.setClassification(classification);
        jeu.setDescription("A test game");
        jeu.setDateDeSortie(dateSortie);
        jeu.setPlateformes(plateformes);
        jeu.setImage("image.jpg");
        jeu.setPrix(59.99f);

        assertEquals(1L, jeu.getId());
        assertEquals("Test Game", jeu.getNom());
        assertEquals(editeur, jeu.getEditeur());
        assertEquals(genre, jeu.getGenre());
        assertEquals(classification, jeu.getClassification());
        assertEquals("A test game", jeu.getDescription());
        assertEquals(dateSortie, jeu.getDateDeSortie());
        assertEquals(plateformes, jeu.getPlateformes());
        assertEquals("image.jpg", jeu.getImage());
        assertEquals(59.99f, jeu.getPrix(), 0.001);
    }

    @Test
    void testEqualsAndHashCode() {
        // Test equals and hashCode from Lombok @Data
        Jeu jeu1 = new Jeu(1L, "Test Game", editeur, genre, classification,
                "A test game", dateSortie, plateformes, "image.jpg", 59.99f);
        Jeu jeu2 = new Jeu(1L, "Test Game", editeur, genre, classification,
                "A test game", dateSortie, plateformes, "image.jpg", 59.99f);
        Jeu jeu3 = new Jeu(2L, "Another Game", editeur, genre, classification,
                "Another test game", dateSortie, plateformes, "image2.jpg", 39.99f);

        assertEquals(jeu1, jeu2);
        assertEquals(jeu1.hashCode(), jeu2.hashCode());
        assertNotEquals(jeu1, jeu3);
        assertNotEquals(jeu1.hashCode(), jeu3.hashCode());
    }

    @Test
    void testToString() {
        // Test toString from Lombok @Data
        Jeu jeu = new Jeu(1L, "Test Game", editeur, genre, classification,
                "A test game", dateSortie, plateformes, "image.jpg", 59.99f);

        String toStringResult = jeu.toString();

        assertNotNull(toStringResult);
        assertTrue(toStringResult.contains("Test Game"));
        assertTrue(toStringResult.contains("59.99"));
    }

    @Test
    void testNonNullConstraint() {
        // Test that the @NonNull constraint is respected
        assertThrows(NullPointerException.class, () -> {
            Jeu jeu = Jeu.builder()
                    .id(1L)
                    // Not setting the required "nom" field
                    .editeur(editeur)
                    .build();
        });
    }
}