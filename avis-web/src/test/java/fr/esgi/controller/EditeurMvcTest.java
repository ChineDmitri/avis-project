package fr.esgi.controller;

import fr.esgi.api.EditeurService;
import fr.esgi.model.Editeur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EditeurMvcTest {

    @Mock
    EditeurService editeurService;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    EditeurMvc editeurMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHome() {
        String result = editeurMvc.home(null);
        assertEquals("hello", result);
    }

    @Test
    void testGetEditeurs() {
        List<Editeur> editeurs = List.of(new Editeur(1L, "Nom", List.of()));
        when(editeurService.recupererEditeurs()).thenReturn(editeurs);

        ModelAndView result = editeurMvc.getEditeurs();
        assertEquals("editeurs", result.getViewName());
        assertEquals(editeurs, result.getModel().get("editeurs"));
    }

    @Test
    void testRechercherEditeurs() {
        String nomRecherche = "Nom";
        List<Editeur> editeurs = List.of(new Editeur(1L, "Nom", List.of()));
        when(editeurService.recupererEditeursParNomContenant(nomRecherche)).thenReturn(editeurs);

        ModelAndView result = editeurMvc.rechercherEditeurs(nomRecherche);
        assertEquals("editeurs", result.getViewName());
        assertEquals(editeurs, result.getModel().get("editeurs"));
        assertEquals(nomRecherche, result.getModel().get("nom"));
    }

    @Test
    void testAfficherFormulaireEditeur() {
        Editeur editeur = new Editeur(1L, "Nom", List.of());
        ModelAndView result = editeurMvc.afficherFormulaireEditeur(editeur);

        assertEquals("editeur", result.getViewName());
    }

    @Test
    void testSauvegarderEditeur_WithErrors() {
        Editeur editeur = new Editeur(1L, "Nom", List.of());
        when(bindingResult.hasErrors()).thenReturn(true);

        ModelAndView result = editeurMvc.sauvegarderEditeur(editeur, bindingResult);
        assertEquals("editeur", result.getViewName());
    }

    @Test
    void testSauvegarderEditeur_Success() {
        Editeur editeur = new Editeur(1L, "Nom", List.of());
        when(bindingResult.hasErrors()).thenReturn(false);
        when(editeurService.ajouterEditeur(editeur)).thenReturn(editeur);

        ModelAndView result = editeurMvc.sauvegarderEditeur(editeur, bindingResult);
        assertEquals("redirect:/editeurs", result.getViewName());
    }
}
