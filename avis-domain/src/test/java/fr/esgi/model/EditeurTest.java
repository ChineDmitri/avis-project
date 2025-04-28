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

class EditeurTest {
    Editeur editeur;

    @BeforeEach
    void setUp() {
        editeur = new Editeur();
        editeur.setNom("nom");
    }

    @Test
    void testSetId() {
        editeur.setId(Long.valueOf(1));
    }

    @Test
    void testSetNom() {
        editeur.setNom("nom");
    }

    @Test
    void testSetJeux() {
        Jeu jeu1 = new Jeu();
        jeu1.setNom("Jeu 1");
        Jeu jeu2 = new Jeu();
        jeu2.setNom("Jeu 2");
        List<Jeu> jeux = List.of(jeu1, jeu2);
        editeur.setJeux(jeux);
    }

    @Test
    void testEquals() {
        boolean result = editeur.equals(editeur);
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = editeur.canEqual("other");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testToString() {
        String result = editeur.toString();
        Assertions.assertEquals("Editeur(id=null, nom=nom)", result);
    }
}
