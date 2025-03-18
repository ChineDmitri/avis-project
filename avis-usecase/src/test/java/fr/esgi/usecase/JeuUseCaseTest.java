package fr.esgi.usecase;

import fr.esgi.adapter.page.PageAdapter;
import fr.esgi.model.*;
import fr.esgi.port.JeuRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JeuUseCaseTest {
    @Mock
    JeuRepository jeuRepository;
    @Mock
    PageAdapter   pageAdapter;
    @InjectMocks
    JeuUseCase    jeuUseCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAjouterJeu() throws
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
        Assert.assertEquals(new Jeu(Long.valueOf(1),
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
    public void testRecupererJeu() throws
                                   Exception {
        when(jeuRepository.findById(anyLong())).thenReturn(new Jeu(Long.valueOf(1),
                                                                   "nom",
                                                                   new Editeur(Long.valueOf(1), "nom", Collections.emptyList()),
                                                                   new Genre(Long.valueOf(1), "nom",  Collections.emptyList()),
                                                                   new Classification("nom", "couleurRGB"),
                                                                   "description",
                                                                   LocalDate.of(2025, Month.MARCH, 18),
                                                                   List.of(new Plateforme("nom")),
                                                                   "image",
                                                                   0f));

        Jeu result = jeuUseCase.recupererJeu(Long.valueOf(1));
        Assert.assertEquals(new Jeu(Long.valueOf(1),
                                    "nom",
                                    new Editeur(Long.valueOf(1), "nom", Collections.emptyList()),
                                    new Genre(Long.valueOf(1), "nom",Collections.emptyList()),
                                    new Classification("nom", "couleurRGB"),
                                    "description",
                                    LocalDate.of(2025, Month.MARCH, 18),
                                    List.of(new Plateforme("nom")),
                                    "image",
                                    0f), result);
    }

//    @Test
//    public void testRecupererJeux() throws
//                                    Exception {
//        when(jeuRepository.findAll(any(PaginationParams.class))).thenReturn(new CustomPagedResult<Jeu>(List.of(new Jeu(Long.valueOf(1),
//                                                                                                                       "nom",
//                                                                                                                       new Editeur(Long.valueOf(1), "nom", List.of(null)),
//                                                                                                                       new Genre(Long.valueOf(1), "nom", List.of(null)),
//                                                                                                                       new Classification("nom", "couleurRGB"),
//                                                                                                                       "description",
//                                                                                                                       LocalDate.of(2025, Month.MARCH, 18),
//                                                                                                                       List.of(new Plateforme("nom")),
//                                                                                                                       "image",
//                                                                                                                       0f)), 0, 0, 0L));
//
//        CustomPagedResult<Jeu> result = jeuUseCase.recupererJeux(new PaginationParams(0, 0));
//        Assert.assertEquals(new CustomPagedResult<Jeu>(List.of(new Jeu(Long.valueOf(1),
//                                                                       "nom",
//                                                                       new Editeur(Long.valueOf(1), "nom", List.of(null)),
//                                                                       new Genre(Long.valueOf(1), "nom", List.of(null)),
//                                                                       new Classification("nom", "couleurRGB"),
//                                                                       "description",
//                                                                       LocalDate.of(2025, Month.MARCH, 18),
//                                                                       List.of(new Plateforme("nom")),
//                                                                       "image",
//                                                                       0f)), 0, 0, 0L), result);
//    }
//
//    @Test
//    public void testEnregistrerJeu() throws
//                                     Exception {
//        when(jeuRepository.save(any(Jeu.class))).thenReturn(new Jeu(Long.valueOf(1),
//                                                                    "nom",
//                                                                    new Editeur(Long.valueOf(1), "nom", List.of(null)),
//                                                                    new Genre(Long.valueOf(1), "nom", List.of(null)),
//                                                                    new Classification("nom", "couleurRGB"),
//                                                                    "description",
//                                                                    LocalDate.of(2025, Month.MARCH, 18),
//                                                                    List.of(new Plateforme("nom")),
//                                                                    "image",
//                                                                    0f));
//
//        Jeu result = jeuUseCase.enregistrerJeu(new Jeu(Long.valueOf(1),
//                                                       "nom",
//                                                       new Editeur(Long.valueOf(1), "nom", List.of(null)),
//                                                       new Genre(Long.valueOf(1), "nom", List.of(null)),
//                                                       new Classification("nom", "couleurRGB"),
//                                                       "description",
//                                                       LocalDate.of(2025, Month.MARCH, 18),
//                                                       List.of(new Plateforme("nom")),
//                                                       "image",
//                                                       0f));
//        Assert.assertEquals(new Jeu(Long.valueOf(1),
//                                    "nom",
//                                    new Editeur(Long.valueOf(1), "nom", List.of(null)),
//                                    new Genre(Long.valueOf(1), "nom", List.of(null)),
//                                    new Classification("nom", "couleurRGB"),
//                                    "description",
//                                    LocalDate.of(2025, Month.MARCH, 18),
//                                    List.of(new Plateforme("nom")),
//                                    "image",
//                                    0f), result);
//    }
//
//    @Test
//    public void testAjouterImage() throws
//                                   Exception {
//        when(jeuRepository.findById(anyLong())).thenReturn(new Jeu(Long.valueOf(1),
//                                                                   "nom",
//                                                                   new Editeur(Long.valueOf(1), "nom", List.of(null)),
//                                                                   new Genre(Long.valueOf(1), "nom", List.of(null)),
//                                                                   new Classification("nom", "couleurRGB"),
//                                                                   "description",
//                                                                   LocalDate.of(2025, Month.MARCH, 18),
//                                                                   List.of(new Plateforme("nom")),
//                                                                   "image",
//                                                                   0f));
//        when(jeuRepository.save(any(Jeu.class))).thenReturn(new Jeu(Long.valueOf(1),
//                                                                    "nom",
//                                                                    new Editeur(Long.valueOf(1), "nom", List.of(null)),
//                                                                    new Genre(Long.valueOf(1), "nom", List.of(null)),
//                                                                    new Classification("nom", "couleurRGB"),
//                                                                    "description",
//                                                                    LocalDate.of(2025, Month.MARCH, 18),
//                                                                    List.of(new Plateforme("nom")),
//                                                                    "image",
//                                                                    0f));
//
//        boolean result = jeuUseCase.ajouterImage(Long.valueOf(1), null);
//        Assert.assertEquals(true, result);
//    }
}
