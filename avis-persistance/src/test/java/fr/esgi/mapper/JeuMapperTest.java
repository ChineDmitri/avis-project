package fr.esgi.mapper;

import fr.esgi.entity.*;
import fr.esgi.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JeuMapperTest {

    @Mock
    private EditeurMapper editeurMapper;

    @Mock
    private PlateformeMapper plateformeMapper;

    @Spy
    private CycleAvoidingMappingContext context;

    private JeuMapper jeuMapper;

    @BeforeEach
    void setUp() {
        jeuMapper = Mappers.getMapper(JeuMapper.class);

        try {
            final java.lang.reflect.Field editeurMapperField = jeuMapper.getClass().getDeclaredField("editeurMapper");
            editeurMapperField.setAccessible(true);
            editeurMapperField.set(jeuMapper, editeurMapper);

            final java.lang.reflect.Field plateformeMapperField = jeuMapper.getClass().getDeclaredField("plateformeMapper");
            plateformeMapperField.setAccessible(true);
            plateformeMapperField.set(jeuMapper, plateformeMapper);
        } catch (final Exception e) {
            throw new RuntimeException("Failed to set up mappers", e);
        }
    }

    @Test
    void testEntityToDomain_BasicMapping() {
        // Arrange
        final EditeurEntity editeurEntity = new EditeurEntity("TestEditeur");
        editeurEntity.setId(1L);

        final JeuEntity jeuEntity = new JeuEntity("TestJeu", editeurEntity);
        jeuEntity.setId(1L);
        jeuEntity.setDescription("Test description");
        jeuEntity.setDateDeSortie(LocalDate.of(2023, 1, 1));
        jeuEntity.setPrix(59.99f);
        jeuEntity.setImage("test-image.jpg");

        // Act
        final Jeu result = jeuMapper.entityToDomain(jeuEntity, context);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("TestJeu", result.getNom());
        assertEquals("Test description", result.getDescription());
        assertEquals(LocalDate.of(2023, 1, 1), result.getDateDeSortie());
        assertEquals(59.99f, result.getPrix());
        assertEquals("test-image.jpg", result.getImage());
    }

    @Test
    void testEntityToDomain_WithAllRelations() {
        // Arrange
        final EditeurEntity editeurEntity = new EditeurEntity("TestEditeur");
        editeurEntity.setId(1L);

        final GenreEntity genreEntity = new GenreEntity();
        genreEntity.setId(1L);
        genreEntity.setNom("RPG");

        final ClassificationEntity classificationEntity = new ClassificationEntity();
        classificationEntity.setId(1L);
        classificationEntity.setNom("PEGI 18");
        classificationEntity.setCouleurRGB("#FF0000");

        final PlateformeEntity plateformeEntity1 = new PlateformeEntity();
        plateformeEntity1.setId(1L);
        plateformeEntity1.setNom("PC");

        final PlateformeEntity plateformeEntity2 = new PlateformeEntity();
        plateformeEntity2.setId(2L);
        plateformeEntity2.setNom("PS5");

        final JeuEntity jeuEntity = new JeuEntity("TestJeu", editeurEntity);
        jeuEntity.setId(1L);
        jeuEntity.setGenre(genreEntity);
        jeuEntity.setClassification(classificationEntity);
        jeuEntity.setPlateformes(Arrays.asList(plateformeEntity1, plateformeEntity2));

        final Plateforme plateforme1 = new Plateforme("PC");
        plateforme1.setId(1L);

        final Plateforme plateforme2 = new Plateforme("PS5");
        plateforme2.setId(2L);

        when(plateformeMapper.entityToDomain(eq(plateformeEntity1), any(CycleAvoidingMappingContext.class)))
                .thenReturn(plateforme1);
        when(plateformeMapper.entityToDomain(eq(plateformeEntity2), any(CycleAvoidingMappingContext.class)))
                .thenReturn(plateforme2);

        // Act
        final Jeu result = jeuMapper.entityToDomain(jeuEntity, context);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertNotNull(result.getGenre());
        assertEquals(1L, result.getGenre().getId());
        assertEquals("RPG", result.getGenre().getNom());
        assertNotNull(result.getClassification());
        assertEquals(1L, result.getClassification().getId());
        assertEquals("PEGI 18", result.getClassification().getNom());

        assertNotNull(result.getPlateformes());
        assertEquals(2, result.getPlateformes().size());
        assertEquals("PC", result.getPlateformes().get(0).getNom());
        assertEquals("PS5", result.getPlateformes().get(1).getNom());
    }

    @Test
    void testEntityToDomain_NullEntity() {
        // Act
        final Jeu result = jeuMapper.entityToDomain(null, context);

        // Assert
        assertNull(result);
    }

    @Test
    void testMapEditeurAvoidingCycle() {
        // Arrange
        final EditeurEntity editeurEntity = new EditeurEntity("TestEditeur");
        editeurEntity.setId(1L);

        when(context.getMappedInstance(editeurEntity, Editeur.class)).thenReturn(null);

        // Act
        final Editeur result = jeuMapper.mapEditeurAvoidingCycle(editeurEntity, context);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("TestEditeur", result.getNom());
        assertNull(result.getJeux()); // Jeux should be null to avoid cycles

        verify(context).storeMappedInstance(eq(editeurEntity), any(Editeur.class));
    }

    @Test
    void testMapEditeurAvoidingCycle_NullEditeur() {
        // Act
        final Editeur result = jeuMapper.mapEditeurAvoidingCycle(null, context);

        // Assert
        assertNull(result);
        verify(context, never()).storeMappedInstance(any(), any());
    }

    @Test
    void testMapEditeurAvoidingCycle_AlreadyMapped() {
        // Arrange
        final EditeurEntity editeurEntity = new EditeurEntity("TestEditeur");
        editeurEntity.setId(1L);

        final Editeur existingEditeur = new Editeur(1L, "TestEditeur", null);

        when(context.getMappedInstance(editeurEntity, Editeur.class)).thenReturn(existingEditeur);

        // Act
        final Editeur result = jeuMapper.mapEditeurAvoidingCycle(editeurEntity, context);

        // Assert
        assertSame(existingEditeur, result); // Should return the existing instance
        verify(context, never()).storeMappedInstance(any(), any()); // Should not store again
    }

    @Test
    void testDomainToEntity_BasicMapping() {
        // Arrange
        final Editeur editeur = new Editeur(1L, "TestEditeur", null);

        final Jeu jeu = new Jeu("TestJeu", editeur);
        jeu.setId(1L);
        jeu.setDescription("Test description");
        jeu.setDateDeSortie(LocalDate.of(2023, 1, 1));
        jeu.setPrix(59.99f);
        jeu.setImage("test-image.jpg");

        final EditeurEntity editeurEntity = new EditeurEntity("TestEditeur");
        editeurEntity.setId(1L);

        when(editeurMapper.domainToEntity(eq(editeur), any(CycleAvoidingMappingContext.class)))
                .thenReturn(editeurEntity);

        // Act
        final JeuEntity result = jeuMapper.domainToEntity(jeu, context);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("TestJeu", result.getNom());
        assertEquals("Test description", result.getDescription());
        assertEquals(LocalDate.of(2023, 1, 1), result.getDateDeSortie());
        assertEquals(59.99f, result.getPrix());
        assertEquals("test-image.jpg", result.getImage());
        assertNotNull(result.getEditeur());
        assertEquals(1L, result.getEditeur().getId());
        assertEquals("TestEditeur", result.getEditeur().getNom());
    }

    @Test
    void testDomainToEntity_WithAllRelations() {
        // Arrange
        final Editeur editeur = new Editeur(1L, "TestEditeur", null);

        final Genre genre = new Genre();
        genre.setId(1L);
        genre.setNom("RPG");

        final Classification classification = new Classification();
        classification.setId(1L);
        classification.setNom("PEGI 18");
        classification.setCouleurRGB("#FF0000");

        final Plateforme plateforme1 = new Plateforme("PC");
        plateforme1.setId(1L);

        final Plateforme plateforme2 = new Plateforme("PS5");
        plateforme2.setId(2L);

        final Jeu jeu = new Jeu("TestJeu", editeur);
        jeu.setId(1L);
        jeu.setGenre(genre);
        jeu.setClassification(classification);
        jeu.setPlateformes(Arrays.asList(plateforme1, plateforme2));

        final EditeurEntity editeurEntity = new EditeurEntity("TestEditeur");
        editeurEntity.setId(1L);

        final GenreEntity genreEntity = new GenreEntity();
        genreEntity.setId(1L);
        genreEntity.setNom("RPG");

        final ClassificationEntity classificationEntity = new ClassificationEntity();
        classificationEntity.setId(1L);
        classificationEntity.setNom("PEGI 18");
        classificationEntity.setCouleurRGB("#FF0000");

        final PlateformeEntity plateformeEntity1 = new PlateformeEntity();
        plateformeEntity1.setId(1L);
        plateformeEntity1.setNom("PC");

        final PlateformeEntity plateformeEntity2 = new PlateformeEntity();
        plateformeEntity2.setId(2L);
        plateformeEntity2.setNom("PS5");

        when(editeurMapper.domainToEntity(eq(editeur), any(CycleAvoidingMappingContext.class)))
                .thenReturn(editeurEntity);
        when(plateformeMapper.domainToEntity(eq(plateforme1), any(CycleAvoidingMappingContext.class)))
                .thenReturn(plateformeEntity1);
        when(plateformeMapper.domainToEntity(eq(plateforme2), any(CycleAvoidingMappingContext.class)))
                .thenReturn(plateformeEntity2);

        // Act
        final JeuEntity result = jeuMapper.domainToEntity(jeu, context);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertNotNull(result.getEditeur());
        assertEquals(1L, result.getEditeur().getId());

        assertNotNull(result.getGenre());
        assertEquals(1L, result.getGenre().getId());
        assertEquals("RPG", result.getGenre().getNom());

        assertNotNull(result.getClassification());
        assertEquals(1L, result.getClassification().getId());
        assertEquals("PEGI 18", result.getClassification().getNom());

        assertNotNull(result.getPlateformes());
        assertEquals(2, result.getPlateformes().size());
    }

    @Test
    void testDomainToEntity_NullDomain() {
        // Act
        final JeuEntity result = jeuMapper.domainToEntity(null, context);

        // Assert
        assertNull(result);
    }
}