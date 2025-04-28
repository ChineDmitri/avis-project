package fr.esgi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class ClassificationTest {
    Classification classification;

    @BeforeEach
    void setUp() {
        classification = new Classification();
        classification.setNom("nomTest");
        classification.setCouleurRGB("couleurRGBTest");
    }

    @Test
    void testSetId() {
        classification.setId(Long.valueOf(1));
    }

    @Test
    void testSetNom() {
        classification.setNom("nom");
    }

    @Test
    void testSetCouleurRGB() {
        classification.setCouleurRGB("couleurRGB");
    }

    @Test
    void testSetJeux() {
        List<Jeu> jeux = new ArrayList<>();
        jeux.add(new Jeu());
        jeux.add(new Jeu());
        classification.setJeux(jeux);
    }


    @Test
    void testEquals() {
        boolean result = classification.equals(classification);
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = classification.canEqual("other");
        Assertions.assertEquals(false, result);
    }


    @Test
    void testToString() {
        String result = classification.toString();
        Assertions.assertEquals("Classification(id=null, nom=nomTest, couleurRGB=couleurRGBTest, jeux=null)", result);
    }
}