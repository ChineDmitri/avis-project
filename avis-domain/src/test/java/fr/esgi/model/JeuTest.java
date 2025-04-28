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

class JeuTest {
    @Mock
    Editeur editeur;
    @Mock
    Genre genre;
    @Mock
    Classification classification;
    //Field dateDeSortie of type LocalDate - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    List<Plateforme> plateformes;
    @InjectMocks
    Jeu jeu;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetId() {
        jeu.setId(Long.valueOf(1));
    }

    @Test
    void testSetNom() {
        jeu.setNom("nom");
    }

    @Test
    void testSetEditeur() {
        final Editeur editeur = new Editeur();
        jeu.setEditeur(editeur);
    }

    @Test
    void testSetGenre() {
        final Genre genre = new Genre();
        jeu.setGenre(genre);
    }

    @Test
    void testSetClassification() {
        jeu.setClassification(new Classification("nom", "couleurRGB"));
    }

    @Test
    void testSetDescription() {
        jeu.setDescription("description");
    }

    @Test
    void testSetDateDeSortie() {
        jeu.setDateDeSortie(LocalDate.of(2025, Month.APRIL, 28));
    }

    @Test
    void testSetPlateformes() {
        jeu.setPlateformes(List.of(new Plateforme("nom")));
    }

    @Test
    void testSetImage() {
        jeu.setImage("image");
    }

    @Test
    void testSetPrix() {
        jeu.setPrix(0f);
    }

    @Test
    void testEquals() {
        boolean result = jeu.equals(jeu);
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = jeu.canEqual("other");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testToString() {
        String result = jeu.toString();
        Assertions.assertEquals("Jeu(id=null, nom=null, editeur=editeur, genre=genre, classification=classification, description=null, dateDeSortie=null, plateformes=plateformes, image=null, prix=0.0)", result);
    }
}