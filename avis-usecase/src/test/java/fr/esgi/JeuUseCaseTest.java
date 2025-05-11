package fr.esgi;

import fr.esgi.model.Jeu;
import fr.esgi.model.Editeur;
import fr.esgi.model.page.CustomPagedResult;
import fr.esgi.model.page.PaginationParams;
import fr.esgi.port.JeuRepository;
import fr.esgi.port.decorator.FileUploaderDecorator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JeuUseCaseTest {

    @Mock
    private JeuRepository jeuRepository;

    @Mock
    private FileUploaderDecorator fileAdapter;

    @InjectMocks
    private JeuUseCase jeuUseCase;

    private Jeu testJeu;
    private PaginationParams testPaginationParams;

    @BeforeEach
    void setUp() {
        // Create test data
        Editeur editeur = new Editeur();
        editeur.setId(1L);
        editeur.setNom("Ubisoft");

        testJeu = Jeu.builder()
                .id(1L)
                .nom("Assassin's Creed")
                .editeur(editeur)
                .description("Un jeu d'action aventure")
                .dateDeSortie(LocalDate.of(2023, 5, 10))
                .image("default.jpg")
                .prix(59.99f)
                .build();

        testPaginationParams = new PaginationParams(0, 10);
    }

    @Test
    void ajouterJeu_shouldSaveAndReturnJeu() {
        // Arrange
        when(jeuRepository.save(testJeu)).thenReturn(testJeu);

        // Act
        Jeu result = jeuUseCase.ajouterJeu(testJeu);

        // Assert
        assertNotNull(result);
        assertEquals(testJeu.getId(), result.getId());
        assertEquals(testJeu.getNom(), result.getNom());
        verify(jeuRepository, times(1)).save(testJeu);
    }

    @Test
    void recupererJeu_shouldReturnJeuById() {
        // Arrange
        when(jeuRepository.findById(1L)).thenReturn(testJeu);

        // Act
        Jeu result = jeuUseCase.recupererJeu(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Assassin's Creed", result.getNom());
        verify(jeuRepository, times(1)).findById(1L);
    }

    @Test
    void recupererJeux_shouldReturnPagedResults() {
        // Arrange
        List<Jeu> jeux = Arrays.asList(testJeu);
        CustomPagedResult<Jeu> expectedResult = new CustomPagedResult<>(jeux, 0, 100, 1);

        when(jeuRepository.findAll(testPaginationParams)).thenReturn(expectedResult);

        // Act
        CustomPagedResult<Jeu> result = jeuUseCase.recupererJeux(testPaginationParams);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        assertEquals("Assassin's Creed", result.getContent().get(0).getNom());
        verify(jeuRepository, times(1)).findAll(testPaginationParams);
    }

    @Test
    void enregistrerJeu_shouldSaveAndReturnJeu() {
        // Arrange
        when(jeuRepository.save(testJeu)).thenReturn(testJeu);

        // Act
        Jeu result = jeuUseCase.enregistrerJeu(testJeu);

        // Assert
        assertNotNull(result);
        assertEquals(testJeu.getId(), result.getId());
        assertEquals(testJeu.getNom(), result.getNom());
        verify(jeuRepository, times(1)).save(testJeu);
    }

    @Test
    void ajouterImage_shouldUploadImageAndUpdateJeu() {
        // Arrange
        Long jeuId = 1L;
        InputStream imageStream = new ByteArrayInputStream("test image content".getBytes());
        String expectedImagePath = "/uploads/image.jpg";

        when(jeuRepository.findById(jeuId)).thenReturn(testJeu);
        when(fileAdapter.upload(imageStream)).thenReturn(expectedImagePath);

        // Act
        String result = jeuUseCase.ajouterImage(jeuId, imageStream);

        // Assert
        assertEquals(expectedImagePath, result);
        assertEquals(expectedImagePath, testJeu.getImage());

        // Verify interactions
        verify(jeuRepository).findById(jeuId);
        verify(fileAdapter).upload(imageStream);
        verify(jeuRepository).save(testJeu);
    }
}