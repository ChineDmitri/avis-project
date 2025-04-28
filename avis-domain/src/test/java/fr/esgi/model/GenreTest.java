package fr.esgi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class GenreTest {
    @Mock
    List<Jeu> jeux;
    @InjectMocks
    Genre genre;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetId() {
        genre.setId(Long.valueOf(1));
    }

    @Test
    void testSetNom() {
        genre.setNom("nom");
    }

    @Test
    void testSetJeux() {
        Jeu jeu1 = new Jeu();
        jeu1.setNom("Jeu 1");
        Jeu jeu2 = new Jeu();
        jeu2.setNom("Jeu 2");
        List<Jeu> jeux = List.of(jeu1, jeu2);
        genre.setJeux(jeux);
    }

    @Test
    void testEquals() {
        boolean result = genre.equals(genre);
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = genre.canEqual("other");
        Assertions.assertEquals(false, result);
    }


    @Test
    void testToString() {
        String result = genre.toString();
        Assertions.assertEquals("Genre(id=null, nom=null)", result);
    }
}