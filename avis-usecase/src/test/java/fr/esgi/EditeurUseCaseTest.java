package fr.esgi;

import fr.esgi.model.*;
import fr.esgi.port.EditeurRepository;
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

class EditeurUseCaseTest {
    @Mock
    EditeurRepository editeurRepository;
    @InjectMocks
    EditeurUseCase editeurUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterEditeur() {
        final Editeur saveEditeur = new Editeur();
        when(editeurRepository.save(any())).thenReturn(saveEditeur);

        Editeur result = editeurUseCase.ajouterEditeur(saveEditeur);
        Assertions.assertEquals(saveEditeur, result);
    }

    @Test
    void testRecupererEditeurs() {
        final List<Editeur> editeurs = List.of(new Editeur());
        when(editeurRepository.findAll()).thenReturn(editeurs);

        List<Editeur> result = editeurUseCase.recupererEditeurs();
        Assertions.assertEquals(result.size(), 1);
        Assertions.assertEquals(editeurs, result);
    }

    @Test
    void testRecupererEditeurParNom() {
        final Editeur editeur = new Editeur();
        editeur.setNom("Test");
        when(editeurRepository.findByNom(anyString())).thenReturn(editeur);
        Editeur result = editeurUseCase.recupererEditeurParNom("Test");
        Assertions.assertEquals(editeur, result);
    }

    @Test
    void testRecupererEditeursParNomContenant() {
        final List<Editeur> editeurs = new ArrayList<>();
        final Editeur editeur = new Editeur();
        editeur.setNom("NomNom");
        editeurs.add(editeur);
        final Editeur editeur2 = new Editeur();
        editeur2.setNom("Nom");
        editeurs.add(editeur2);
        when(editeurRepository.findByNomContainingIgnoreCase(anyString())).thenReturn(editeurs);

        List<Editeur> result = editeurUseCase.recupererEditeursParNomContenant("nom");
        Assertions.assertEquals(editeurs, result);
    }

    @Test
    void testRecupererEditeur() {
        final Editeur editeur = new Editeur();
        when(editeurRepository.findById(anyLong())).thenReturn(java.util.Optional.of(editeur));

        Editeur result = editeurUseCase.recupererEditeur(Long.valueOf(1));
        Assertions.assertEquals(editeur, result);
    }

    @Test
    void testSupprimerEditeur() {
        editeurUseCase.supprimerEditeur(Long.valueOf(1));
    }
}