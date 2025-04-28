package fr.esgi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilisateurTest {
    Utilisateur utilisateur = new Moderateur();

    @Test
    void testSetId() {
        utilisateur.setId(Long.valueOf(1));
    }

    @Test
    void testSetPseudo() {
        utilisateur.setPseudo("pseudo");
    }

    @Test
    void testSetMotDePasse() {
        utilisateur.setMotDePasse("motDePasse");
    }

    @Test
    void testSetEmail() {
        utilisateur.setEmail("email");
    }

    @Test
    void testEquals() {
        final Utilisateur utilisateur1 = utilisateur;
        boolean result = utilisateur.equals(utilisateur1);
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = utilisateur.canEqual("other");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testToString() {
        String result = utilisateur.toString();
        Assertions.assertEquals("Moderateur(numeroDeTelephone=null)", result);
    }
}