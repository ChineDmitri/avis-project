package fr.esgi.controller.rest;

import fr.esgi.api.JeuService;
import fr.esgi.dto.*;
import fr.esgi.mapper.JeuDtoMapper;
import fr.esgi.model.*;
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
import static fr.esgi.utils.avisWebUtils.*;

class JeuRestControllerTest {
    @Mock
    JeuService jeuService;
    @Mock
    JeuDtoMapper jeuMapper;
    @InjectMocks
    JeuRestController jeuRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostJeu() {
        // Arrange
        final Jeu jeu = createJeu();

        when(jeuService.ajouterJeu(any(Jeu.class))).thenReturn(jeu);
        when(jeuMapper.toModel(any(JeuDto.class))).thenReturn(jeu);

        // Act
        final Jeu result = jeuRestController.postJeu(createJeuDto(), null);

        // Assert
        Assertions.assertEquals(jeu, result);
    }
}

