package fr.esgi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ModerateurTest {
    final Moderateur moderateur = new Moderateur();

    @Test
    void testSetNumeroDeTelephone() {
        moderateur.setNumeroDeTelephone("numeroDeTelephone");
    }

    @Test
    void testEquals() {
        Moderateur moderateur1 = moderateur;
        boolean result = moderateur.equals(moderateur1);
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = moderateur.canEqual("other");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testToString() {
        String result = moderateur.toString();
        Assertions.assertEquals("Moderateur(numeroDeTelephone=null)", result);
    }
    @Test
    void testSetId() {
        moderateur.setId(Long.valueOf(1));
    }

    @Test
    void testSetPseudo() {
        moderateur.setPseudo("pseudo");
    }

    @Test
    void testSetMotDePasse() {
        moderateur.setMotDePasse("motDePasse");
    }

    @Test
    void testSetEmail() {
        moderateur.setEmail("email");
    }
}

