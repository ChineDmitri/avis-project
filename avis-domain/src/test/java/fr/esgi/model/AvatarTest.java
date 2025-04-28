package fr.esgi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class AvatarTest {
    @Mock
    Joueur joueur;
    @InjectMocks
    Avatar avatar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetId() {
        avatar.setId(Long.valueOf(1));
    }

    @Test
    void testSetNom() {
        avatar.setNom("nom");
    }

    @Test
    void testSetJoueur() {
        avatar.setJoueur(new Joueur());
    }

    @Test
    void testEquals() {
        boolean result = avatar.equals(avatar);
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = avatar.canEqual("avatar");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testToString() {
        String result = avatar.toString();
        Assertions.assertEquals("Avatar(id=null, nom=null, joueur=joueur)", result);
    }
}