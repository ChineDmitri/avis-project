package fr.esgi.mapper;

import fr.esgi.dto.*;
import fr.esgi.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JeuDtoMapperTest {

    private JeuDtoMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(JeuDtoMapper.class);
    }

    @Test
    void toModel_shouldMapAllFields() {
        // Arrange
        EditeurDto editeurDto = new EditeurDto(1L, "Ubisoft");
        GenreDto genreDto = new GenreDto(1L, "Action");
        ClassificationDto classificationDto = new ClassificationDto(1L, "PEGI 18", "#FF0000");

        JeuDto jeuDtoTemp = new JeuDto(1L, "Assassin's Creed", editeurDto, genreDto,
                classificationDto, "Un jeu d'action aventure", LocalDate.of(2023, 5, 10), null);

        List<JeuDto> jeuxDto = new ArrayList<>();
        jeuxDto.add(jeuDtoTemp);
        List<PlateformeDto> plateformesDto = Arrays.asList(
                new PlateformeDto("PlayStation 5", "2020-11-12", jeuxDto),
                new PlateformeDto("Xbox Series X", "2020-11-10", jeuxDto)
        );

        JeuDto jeuDto = new JeuDto(1L, "Assassin's Creed", editeurDto, genreDto,
                classificationDto, "Un jeu d'action aventure", LocalDate.of(2023, 5, 10), plateformesDto);

        // Act
        Jeu result = mapper.toModel(jeuDto);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Assassin's Creed", result.getNom());
        assertEquals("Un jeu d'action aventure", result.getDescription());
        assertEquals(LocalDate.of(2023, 5, 10), result.getDateDeSortie());

        assertNotNull(result.getEditeur());
        assertEquals(1L, result.getEditeur().getId());
        assertEquals("Ubisoft", result.getEditeur().getNom());

        assertNotNull(result.getGenre());
        assertEquals(1L, result.getGenre().getId());
        assertEquals("Action", result.getGenre().getNom());

        assertNotNull(result.getClassification());
        assertEquals(1L, result.getClassification().getId());
        assertEquals("PEGI 18", result.getClassification().getNom());
        assertEquals("#FF0000", result.getClassification().getCouleurRGB());

        assertNotNull(result.getPlateformes());
        assertEquals(2, result.getPlateformes().size());
        assertEquals("PlayStation 5", result.getPlateformes().get(0).getNom());
        assertEquals("Xbox Series X", result.getPlateformes().get(1).getNom());
        // Check that dates are correctly mapped from String to LocalDate
        assertNotNull(result.getPlateformes().get(0).getDateDeSortie());
        assertEquals(LocalDate.parse("2020-11-12"), result.getPlateformes().get(0).getDateDeSortie());
    }

    @Test
    void toDto_shouldMapAllFields() {
        // Arrange
        Editeur editeur = new Editeur();
        editeur.setId(1L);
        editeur.setNom("Ubisoft");

        Genre genre = Genre.builder()
                .id(1L)
                .nom("Action")
                .build();

        Classification classification = new Classification("PEGI 18", "#FF0000");
        classification.setId(1L);

        Jeu jeuTemp = new Jeu("Assassin's Creed", LocalDate.of(2023, 5, 10), editeur);
        jeuTemp.setId(1L);
        jeuTemp.setGenre(genre);
        jeuTemp.setClassification(classification);
        jeuTemp.setDescription("Un jeu d'action aventure");
        jeuTemp.setImage("ac.jpg");
        jeuTemp.setPrix(59.99f);

        List<Jeu> jeux = new ArrayList<>();
        jeux.add(jeuTemp);

        Plateforme ps5 = new Plateforme("PlayStation 5");
        ps5.setId(1L);
        ps5.setJeux(jeux);
        ps5.setDateDeSortie(LocalDate.of(2020, 11, 12));

        Plateforme xbox = new Plateforme("Xbox Series X");
        xbox.setId(2L);
        xbox.setJeux(jeux);
        xbox.setDateDeSortie(LocalDate.of(2020, 11, 10));

        List<Plateforme> plateformes = Arrays.asList(ps5, xbox);

        Jeu jeu = new Jeu("Assassin's Creed", LocalDate.of(2023, 5, 10), editeur);
        jeu.setId(1L);
        jeu.setGenre(genre);
        jeu.setClassification(classification);
        jeu.setDescription("Un jeu d'action aventure");
        jeu.setPlateformes(plateformes);
        jeu.setImage("ac.jpg");
        jeu.setPrix(59.99f);
        jeu.setEditeur(editeur);

        editeur.setJeux(jeux);
        genre.setJeux(jeux);
        classification.setJeux(jeux);

        // Act
        JeuDto result = mapper.toDto(jeu);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Assassin's Creed", result.getNom());
        assertEquals("Un jeu d'action aventure", result.getDescription());
        assertEquals(LocalDate.of(2023, 5, 10), result.getDateDeSortie());

        assertNotNull(result.getEditeur());
        assertEquals(1L, result.getEditeur().getId());
        assertEquals("Ubisoft", result.getEditeur().getNom());

        assertNotNull(result.getGenre());
        assertEquals(1L, result.getGenre().getId());
        assertEquals("Action", result.getGenre().getNom());

        assertNotNull(result.getClassification());
        assertEquals(1L, result.getClassification().getId());
        assertEquals("PEGI 18", result.getClassification().getNom());
        assertEquals("#FF0000", result.getClassification().getCouleurRGB());

        assertNotNull(result.getPlateformes());
        assertEquals(2, result.getPlateformes().size());
        assertEquals("PlayStation 5", result.getPlateformes().get(0).getNom());
        assertEquals("2020-11-12", result.getPlateformes().get(0).getDateDeSortie());
        assertEquals("Xbox Series X", result.getPlateformes().get(1).getNom());
        assertEquals("2020-11-10", result.getPlateformes().get(1).getDateDeSortie());
    }

    @Test
    void partialUpdate_shouldUpdateOnlyNonNullFields() {
        // Arrange
        Editeur originalEditeur = new Editeur();
        originalEditeur.setId(1L);
        originalEditeur.setNom("Ubisoft");

        Genre originalGenre = Genre.builder()
                .id(1L)
                .nom("Action")
                .build();

        Classification originalClassification = new Classification("PEGI 18", "#FF0000");
        originalClassification.setId(1L);

        Jeu originalJeu = new Jeu("Assassin's Creed", LocalDate.of(2023, 5, 10), originalEditeur);
        originalJeu.setId(1L);
        originalJeu.setGenre(originalGenre);
        originalJeu.setClassification(originalClassification);
        originalJeu.setDescription("Un jeu d'action aventure");
        originalJeu.setImage("ac.jpg");
        originalJeu.setPrix(59.99f);

        Plateforme ps5 = new Plateforme("PlayStation 5");
        ps5.setId(1L);
        ps5.setDateDeSortie(LocalDate.of(2020, 11, 12));

        Plateforme xbox = new Plateforme("Xbox Series X");
        xbox.setId(2L);
        xbox.setDateDeSortie(LocalDate.of(2020, 11, 10));

        List<Plateforme> originalPlateformes = Arrays.asList(ps5, xbox);
        originalJeu.setPlateformes(originalPlateformes);

        EditeurDto newEditeurDto = new EditeurDto(2L, "EA");

        JeuDto updateDto = new JeuDto(1L, "Assassin's Creed Valhalla", newEditeurDto,
                null, null, "Un jeu d'action aventure Viking", null, null);

        // Act
        Jeu result = mapper.partialUpdate(updateDto, originalJeu);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());

        assertEquals("Assassin's Creed Valhalla", result.getNom());
        assertEquals("Un jeu d'action aventure Viking", result.getDescription());
        assertEquals(2L, result.getEditeur().getId());
        assertEquals("EA", result.getEditeur().getNom());

        assertEquals(originalGenre, result.getGenre());
        assertEquals(originalClassification, result.getClassification());
        assertEquals(LocalDate.of(2023, 5, 10), result.getDateDeSortie());
        assertEquals(originalPlateformes, result.getPlateformes());
        assertEquals("ac.jpg", result.getImage());
        assertEquals(59.99f, result.getPrix());
    }
}