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

class PlateformeTest {

    Plateforme plateforme;

    @BeforeEach
    void setUp() {
        plateforme = new Plateforme();
        plateforme.setNom("nom");
    }

    @Test
    void testSetId() {
        plateforme.setId(Long.valueOf(1));
    }

    @Test
    void testSetNom() {
        plateforme.setNom("nom");
    }

    @Test
    void testSetJeux() {
        Jeu jeu1 = new Jeu();
        jeu1.setNom("Jeu 1");
        Jeu jeu2 = new Jeu();
        jeu2.setNom("Jeu 2");
        List<Jeu> jeux = List.of(jeu1, jeu2);
        plateforme.setJeux(jeux);
    }

    @Test
    void testSetDateDeSortie() {
        plateforme.setDateDeSortie(LocalDate.of(2025, Month.APRIL, 28));
    }

    @Test
    void testEquals() {
        final Plateforme plateforme1 = plateforme;
        boolean result = plateforme.equals(plateforme1);
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = plateforme.canEqual("other");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testToString() {
        String result = plateforme.toString();
        Assertions.assertEquals("Plateforme(id=null, nom=nom, dateDeSortie=null)", result);
    }
}