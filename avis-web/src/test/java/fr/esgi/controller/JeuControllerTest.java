package fr.esgi.controller;

import fr.esgi.adapter.page.PageAdapter;
import fr.esgi.api.JeuService;
import fr.esgi.exception.TechnicalException;
import fr.esgi.model.Jeu;
import fr.esgi.model.page.CustomPagedResult;
import fr.esgi.model.page.PaginationParams;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static fr.esgi.utils.avisWebUtils.createJeu;

class JeuControllerTest {

    @Mock
    JeuService jeuService;
    @Mock
    PageAdapter pageAdapter;
    @InjectMocks
    JeuController jeuController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetJeux() {
        CustomPagedResult<Jeu> customPagedResult = new CustomPagedResult<>(new ArrayList<>(), 0, 0, 0L);
        Pageable pageable = PageRequest.of(0, 10, Sort.by("nom").ascending());

        when(pageAdapter.toPaginationParams(pageable)).thenReturn(new PaginationParams(0, 10));
        when(jeuService.recupererJeux(any(PaginationParams.class))).thenReturn(customPagedResult);

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getAttribute("dateHeureDebut")).thenReturn(new Date().getTime() - 100L);

        ModelAndView result = jeuController.getJeux(pageable, request);

        assertEquals("jeux", result.getViewName());
        assertTrue(result.getModel().containsKey("pageDeJeux"));
        assertTrue(result.getModel().containsKey("sort"));
        assertTrue(result.getModel().containsKey("tempsDeTraitementEnMs"));
    }

    @Test
    void testGetTeleversement() {

        final Jeu jeu = createJeu();
        when(jeuService.recupererJeu(1L)).thenReturn(jeu);

        ModelAndView result = jeuController.getTeleversement(1L);

        assertEquals("televersement", result.getViewName());
        assertTrue(result.getModel().containsKey("jeu"));
        assertEquals(jeu, result.getModel().get("jeu"));
    }

    @Test
    void testPostTeleversement() throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile("file", "test.png", "image/png", "dummy-image-content".getBytes());
        when(jeuService.ajouterImage(eq(1L), any(InputStream.class))).thenReturn("some/path/test.png");

        ModelAndView result = jeuController.postTeleversement(1L, mockFile);

        assertEquals("redirect:/jeux", result.getViewName());
    }

    @Test
    void testPostTeleversement_throwsTechnicalException() {
        MultipartFile mockFile = mock(MultipartFile.class);
        try {
            when(mockFile.getInputStream()).thenThrow(new IOException("Erreur de lecture"));
            assertThrows(TechnicalException.class, () -> jeuController.postTeleversement(1L, mockFile));
        } catch (Exception e) {
            fail("Test failed unexpectedly: " + e.getMessage());
        }
    }
}
