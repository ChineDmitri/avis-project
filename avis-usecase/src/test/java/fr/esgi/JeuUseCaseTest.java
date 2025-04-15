package fr.esgi;

import fr.esgi.adapter.page.PageAdapter;
import fr.esgi.model.*;
import fr.esgi.port.JeuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class JeuUseCaseTest {
    @Mock
    JeuRepository jeuRepository;
    @Mock
    PageAdapter   pageAdapter;
    @InjectMocks
    JeuUseCase    jeuUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAjouterJeu() throws
                                 Exception {
        when(jeuRepository.save(any(Jeu.class))).thenReturn(new Jeu(Long.valueOf(1),
                                                                    "nom",
                                                                    new Editeur(Long.valueOf(1), "nom", Collections.emptyList()),
                                                                    new Genre(Long.valueOf(1), "nom", Collections.emptyList()),
                                                                    new Classification("nom", "couleurRGB"),
                                                                    "description",
                                                                    LocalDate.of(2025, Month.MARCH, 18),
                                                                    List.of(new Plateforme("nom")),
                                                                    "image",
                                                                    0f));

        Jeu result = jeuUseCase.ajouterJeu(new Jeu(Long.valueOf(1),
                                                   "nom",
                                                   new Editeur(Long.valueOf(1), "nom", Collections.emptyList()),
                                                   new Genre(Long.valueOf(1), "nom", Collections.emptyList()),
                                                   new Classification("nom", "couleurRGB"),
                                                   "description",
                                                   LocalDate.of(2025, Month.MARCH, 18),
                                                   List.of(new Plateforme("nom")),
                                                   "image",
                                                   0f));
        assertEquals(new Jeu(Long.valueOf(1),
                             "nom",
                             new Editeur(Long.valueOf(1), "nom", Collections.emptyList()),
                             new Genre(Long.valueOf(1), "nom", Collections.emptyList()),
                             new Classification("nom", "couleurRGB"),
                             "description",
                             LocalDate.of(2025, Month.MARCH, 18),
                             List.of(new Plateforme("nom")),
                             "image",
                             0f), result);
    }

    @Test
    void testRecupererJeu() throws
                                   Exception {
        when(jeuRepository.findById(anyLong())).thenReturn(new Jeu(Long.valueOf(1),
                                                                   "nom",
                                                                   new Editeur(Long.valueOf(1), "nom", Collections.emptyList()),
                                                                   new Genre(Long.valueOf(1), "nom", Collections.emptyList()),
                                                                   new Classification("nom", "couleurRGB"),
                                                                   "description",
                                                                   LocalDate.of(2025, Month.MARCH, 18),
                                                                   List.of(new Plateforme("nom")),
                                                                   "image",
                                                                   0f));

        Jeu result = jeuUseCase.recupererJeu(Long.valueOf(1));
        assertEquals(new Jeu(Long.valueOf(1),
                             "nom",
                             new Editeur(Long.valueOf(1), "nom", Collections.emptyList()),
                             new Genre(Long.valueOf(1), "nom", Collections.emptyList()),
                             new Classification("nom", "couleurRGB"),
                             "description",
                             LocalDate.of(2025, Month.MARCH, 18),
                             List.of(new Plateforme("nom")),
                             "image",
                             0f), result);
    }
}
