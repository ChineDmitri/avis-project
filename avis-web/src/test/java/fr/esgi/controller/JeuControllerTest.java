package fr.esgi.controller;

import fr.esgi.adapter.page.PageAdapter;
import fr.esgi.api.JeuService;
import fr.esgi.exception.TechnicalException;
import fr.esgi.model.Jeu;
import fr.esgi.model.page.CustomPagedResult;
import fr.esgi.model.page.PaginationParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;

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
        when(jeuService.recupererJeux(any(PaginationParams.class))).thenReturn(new CustomPagedResult<Jeu>(List.of(new Jeu(Long.valueOf(1), "nom", null, null, null, "description", LocalDate.of(2025, Month.MAY, 10), List.of(null), "image", 0f)), 0, 0, 0L));
        when(pageAdapter.toPaginationParams(any(Pageable.class))).thenReturn(new PaginationParams(0, 0));

        ModelAndView result = jeuController.getJeux(null, null);
        Assertions.assertEquals(new ModelAndView("viewName", "modelName", "modelObject"), result);
    }

    @Test
    void testGetTeleversement() {
        when(jeuService.recupererJeu(anyLong())).thenReturn(new Jeu(Long.valueOf(1), "nom", null, null, null, "description", LocalDate.of(2025, Month.MAY, 10), List.of(null), "image", 0f));

        ModelAndView result = jeuController.getTeleversement(Long.valueOf(1));
        Assertions.assertEquals(new ModelAndView("viewName", "modelName", "modelObject"), result);
    }

    @Test
    void testPostTeleversement() throws TechnicalException {
        when(jeuService.ajouterImage(anyLong(), any(InputStream.class))).thenReturn("ajouterImageResponse");

        ModelAndView result = jeuController.postTeleversement(Long.valueOf(1), null);
        Assertions.assertEquals(new ModelAndView("viewName", "modelName", "modelObject"), result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme