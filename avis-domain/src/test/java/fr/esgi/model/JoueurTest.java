package fr.esgi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;

class JoueurTest {
    @Mock
    List<Avis> avis;
    @Mock
    Avatar avatar;
    @InjectMocks
    Joueur joueur;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetDateDeNaissance() {
        joueur.setDateDeNaissance(LocalDate.of(2025, Month.APRIL, 28));
    }

    @Test
    void testSetAvis() {
        final List<Avis> avis = List.of(new Avis());
        joueur.setAvis(avis);
    }

    @Test
    void testSetAvatar() {
        final Avatar avatar = new Avatar();
        joueur.setAvatar(avatar);
    }

    @Test
    void testEquals() {
        final Joueur joueur1 = joueur;
        boolean result = joueur.equals(joueur1);
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = joueur.canEqual("other");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testToString() {
        String result = joueur.toString();
        Assertions.assertEquals("Joueur(super=Utilisateur(id=null, pseudo=null, motDePasse=null, email=null), dateDeNaissance=null, avis=avis, avatar=avatar)", result);
    }

    @Test
    void testSetId() {
        joueur.setId(Long.valueOf(1));
    }

    @Test
    void testSetPseudo() {
        joueur.setPseudo("pseudo");
    }

    @Test
    void testSetMotDePasse() {
        joueur.setMotDePasse("motDePasse");
    }

    @Test
    void testSetEmail() {
        joueur.setEmail("email");
    }
}
