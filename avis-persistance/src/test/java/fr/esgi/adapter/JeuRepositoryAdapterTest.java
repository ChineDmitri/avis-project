package fr.esgi.adapter;

import fr.esgi.adapter.page.PageAdapter;
import fr.esgi.entity.EditeurEntity;
import fr.esgi.entity.GenreEntity;
import fr.esgi.entity.JeuEntity;
import fr.esgi.entity.PlateformeEntity;
import fr.esgi.mapper.CycleAvoidingMappingContext;
import fr.esgi.mapper.EditeurMapper;
import fr.esgi.mapper.JeuMapper;
import fr.esgi.mapper.PlateformeMapper;
import fr.esgi.model.Editeur;
import fr.esgi.model.Genre;
import fr.esgi.model.Jeu;
import fr.esgi.model.Plateforme;
import fr.esgi.model.page.CustomPagedResult;
import fr.esgi.model.page.PaginationParams;
import fr.esgi.repository.JeuJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JeuRepositoryAdapterTest {

    @Mock
    private JeuJpaRepository jeuJpaRepository;

    @Mock
    private JeuMapper jeuMapper;

    @Mock
    private EditeurMapper editeurMapper;

    @Mock
    private PlateformeMapper plateformeMapper;

    @Mock
    private PageAdapter pageAdapter;

    @InjectMocks
    private JeuRepositoryAdapter jeuRepositoryAdapter;

    private Jeu jeu;
    private JeuEntity jeuEntity;
    private Editeur editeur;
    private Genre genre;
    private Plateforme plateforme;
    private Long jeuId;
    private String jeuNom;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    @BeforeEach
    void setUp() {
        jeuId = 1L;
        jeuNom = "Test Game";
        jeu = new Jeu();
        jeuEntity = new JeuEntity();
        editeur = new Editeur();
        genre = new Genre();
        genre.setId(1L);
        genre.setNom("Genre");
        plateforme = new Plateforme();
        dateDebut = LocalDate.of(2020, 1, 1);
        dateFin = LocalDate.of(2023, 12, 31);
    }

    @Test
    void testFindById() {
        // Arrange
        when(jeuJpaRepository.findById(jeuId)).thenReturn(Optional.of(jeuEntity));
        when(jeuMapper.entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class))).thenReturn(jeu);

        // Act
        final Jeu result = jeuRepositoryAdapter.findById(jeuId);

        // Assert
        assertNotNull(result);
        assertEquals(jeu, result);
        verify(jeuJpaRepository).findById(jeuId);
        verify(jeuMapper).entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindById_NotFound() {
        // Arrange
        when(jeuJpaRepository.findById(jeuId)).thenReturn(Optional.empty());

        // Act
        final Jeu result = jeuRepositoryAdapter.findById(jeuId);

        // Assert
        assertNull(result);
        verify(jeuJpaRepository).findById(jeuId);
        verify(jeuMapper, never()).entityToDomain(any(), any());
    }

    @Test
    void testFindAll() {
        // Arrange
        final List<JeuEntity> jeuEntities = Collections.singletonList(jeuEntity);
        when(jeuJpaRepository.findAll()).thenReturn(jeuEntities);
        when(jeuMapper.entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class))).thenReturn(jeu);

        // Act
        final List<Jeu> result = jeuRepositoryAdapter.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(jeu, result.get(0));
        verify(jeuJpaRepository).findAll();
        verify(jeuMapper).entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindAllPaginated() {
        // Arrange
        final PaginationParams paginationParams = new PaginationParams(1,1);
        final Pageable pageable = mock(Pageable.class);
        final Page<JeuEntity> page = mock(Page.class);
        final CustomPagedResult<Jeu> customPagedResult = mock(CustomPagedResult.class);

        when(pageAdapter.toSpringPageable(paginationParams)).thenReturn(pageable);
        when(jeuJpaRepository.findAll(pageable)).thenReturn(page);
        when(pageAdapter.fromSpringPage(eq(page), any(Function.class))).thenReturn(customPagedResult);

        // Act
        final CustomPagedResult<Jeu> result = jeuRepositoryAdapter.findAll(paginationParams);

        // Assert
        assertNotNull(result);
        assertEquals(customPagedResult, result);
        verify(pageAdapter).toSpringPageable(paginationParams);
        verify(jeuJpaRepository).findAll(pageable);
        verify(pageAdapter).fromSpringPage(eq(page), any(Function.class));
    }

    @Test
    void testFindFirstByNom() {
        // Arrange
        when(jeuJpaRepository.findFirstByNom(jeuNom)).thenReturn(jeuEntity);
        when(jeuMapper.entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class))).thenReturn(jeu);

        // Act
        final Jeu result = jeuRepositoryAdapter.findFirstByNom(jeuNom);

        // Assert
        assertNotNull(result);
        assertEquals(jeu, result);
        verify(jeuJpaRepository).findFirstByNom(jeuNom);
        verify(jeuMapper).entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindFirstByNom_NotFound() {
        // Arrange
        when(jeuJpaRepository.findFirstByNom(jeuNom)).thenReturn(null);

        // Act
        final Jeu result = jeuRepositoryAdapter.findFirstByNom(jeuNom);

        // Assert
        assertNull(result);
        verify(jeuJpaRepository).findFirstByNom(jeuNom);
        verify(jeuMapper, never()).entityToDomain(any(), any());
    }

    @Test
    void testFindByEditeur() {
        // Arrange
        final EditeurEntity editeurEntity = mock(EditeurEntity.class);
        final List<JeuEntity> jeuEntities = Collections.singletonList(jeuEntity);

        when(editeurMapper.domainToEntity(eq(editeur), any(CycleAvoidingMappingContext.class))).thenReturn(editeurEntity);
        when(jeuJpaRepository.findByEditeur(editeurEntity)).thenReturn(jeuEntities);
        when(jeuMapper.entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class))).thenReturn(jeu);

        // Act
        final List<Jeu> result = jeuRepositoryAdapter.findByEditeur(editeur);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(jeu, result.get(0));
        verify(editeurMapper).domainToEntity(eq(editeur), any(CycleAvoidingMappingContext.class));
        verify(jeuJpaRepository).findByEditeur(editeurEntity);
        verify(jeuMapper).entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindByEditeurNom() {
        // Arrange
        final String editeurNom = "Test Publisher";
        final List<JeuEntity> jeuEntities = Collections.singletonList(jeuEntity);

        when(jeuJpaRepository.findByEditeurNom(editeurNom)).thenReturn(jeuEntities);
        when(jeuMapper.entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class))).thenReturn(jeu);

        // Act
        final List<Jeu> result = jeuRepositoryAdapter.findByEditeurNom(editeurNom);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(jeu, result.get(0));
        verify(jeuJpaRepository).findByEditeurNom(editeurNom);
        verify(jeuMapper).entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindByEditeurAndGenre() {
        // Arrange
        final EditeurEntity editeurEntity1 = mock(EditeurEntity.class);
        final List<JeuEntity> jeuEntities = Collections.singletonList(jeuEntity);

        when(editeurMapper.domainToEntity(eq(editeur), any(CycleAvoidingMappingContext.class))).thenReturn(editeurEntity1);
        when(jeuJpaRepository.findByEditeurAndGenre(eq(editeurEntity1), any(GenreEntity.class))).thenReturn(jeuEntities);
        when(jeuMapper.entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class))).thenReturn(jeu);

        // Act
        final List<Jeu> result = jeuRepositoryAdapter.findByEditeurAndGenre(editeur, genre);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(jeu, result.get(0));
        verify(editeurMapper).domainToEntity(eq(editeur), any(CycleAvoidingMappingContext.class));

        final ArgumentCaptor<GenreEntity> genreEntityCaptor = ArgumentCaptor.forClass(GenreEntity.class);
        verify(jeuJpaRepository).findByEditeurAndGenre(eq(editeurEntity1), genreEntityCaptor.capture());
        assertEquals(genre.getId(), genreEntityCaptor.getValue().getId());

        verify(jeuMapper).entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindTop5ByEditeurOrderByDateDeSortieDesc() {
        // Arrange
        final EditeurEntity editeurEntity = mock(EditeurEntity.class);
        final List<JeuEntity> jeuEntities = Collections.singletonList(jeuEntity);

        when(editeurMapper.domainToEntity(eq(editeur), any(CycleAvoidingMappingContext.class))).thenReturn(editeurEntity);
        when(jeuJpaRepository.findTop5ByEditeurOrderByDateDeSortieDesc(editeurEntity)).thenReturn(jeuEntities);
        when(jeuMapper.entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class))).thenReturn(jeu);

        // Act
        final List<Jeu> result = jeuRepositoryAdapter.findTop5ByEditeurOrderByDateDeSortieDesc(editeur);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(jeu, result.get(0));
        verify(editeurMapper).domainToEntity(eq(editeur), any(CycleAvoidingMappingContext.class));
        verify(jeuJpaRepository).findTop5ByEditeurOrderByDateDeSortieDesc(editeurEntity);
        verify(jeuMapper).entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindByGenre() {
        // Arrange
        final List<JeuEntity> jeuEntities = Collections.singletonList(jeuEntity);

        when(jeuJpaRepository.findByGenre(any(GenreEntity.class))).thenReturn(jeuEntities);
        when(jeuMapper.entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class))).thenReturn(jeu);

        // Act
        final List<Jeu> result = jeuRepositoryAdapter.findByGenre(genre);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(jeu, result.get(0));

        // Capture the GenreEntity to verify it's built correctly
        final ArgumentCaptor<GenreEntity> genreEntityCaptor = ArgumentCaptor.forClass(GenreEntity.class);
        verify(jeuJpaRepository).findByGenre(genreEntityCaptor.capture());
        assertEquals(genre.getId(), genreEntityCaptor.getValue().getId());

        verify(jeuMapper).entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindByGenreNom() {
        // Arrange
        final String genreNom = "Action";
        final List<JeuEntity> jeuEntities = Collections.singletonList(jeuEntity);

        when(jeuJpaRepository.findByGenreNom(genreNom)).thenReturn(jeuEntities);
        when(jeuMapper.entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class))).thenReturn(jeu);

        // Act
        final List<Jeu> result = jeuRepositoryAdapter.findByGenreNom(genreNom);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(jeu, result.get(0));
        verify(jeuJpaRepository).findByGenreNom(genreNom);
        verify(jeuMapper).entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindByNomLike() {
        // Arrange
        final List<JeuEntity> jeuEntities = Collections.singletonList(jeuEntity);

        when(jeuJpaRepository.findByNomLike(jeuNom)).thenReturn(jeuEntities);
        when(jeuMapper.entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class))).thenReturn(jeu);

        // Act
        final List<Jeu> result = jeuRepositoryAdapter.findByNomLike(jeuNom);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(jeu, result.get(0));
        verify(jeuJpaRepository).findByNomLike(jeuNom);
        verify(jeuMapper).entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testSave() {
        // Arrange
        when(jeuMapper.domainToEntity(eq(jeu), any(CycleAvoidingMappingContext.class))).thenReturn(jeuEntity);
        when(jeuJpaRepository.save(jeuEntity)).thenReturn(jeuEntity);
        when(jeuMapper.entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class))).thenReturn(jeu);

        // Act
        final Jeu result = jeuRepositoryAdapter.save(jeu);

        // Assert
        assertNotNull(result);
        assertEquals(jeu, result);
        verify(jeuMapper).domainToEntity(eq(jeu), any(CycleAvoidingMappingContext.class));
        verify(jeuJpaRepository).save(jeuEntity);
        verify(jeuMapper).entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testDeleteByEditeur() {
        // Arrange
        final EditeurEntity editeurEntity2 = mock(EditeurEntity.class);
        final long expectedDeleteCount = 5L;

        when(editeurMapper.domainToEntity(eq(editeur), any(CycleAvoidingMappingContext.class))).thenReturn(editeurEntity2);
        when(jeuJpaRepository.deleteByEditeur(editeurEntity2)).thenReturn(expectedDeleteCount);

        // Act
        final long result = jeuRepositoryAdapter.deleteByEditeur(editeur);

        // Assert
        assertEquals(expectedDeleteCount, result);
        verify(editeurMapper).domainToEntity(eq(editeur), any(CycleAvoidingMappingContext.class));
        verify(jeuJpaRepository).deleteByEditeur(editeurEntity2);
    }

    @Test
    void testFindByNomEndingWith() {
        // Arrange
        final String filtre = "Game";
        final List<JeuEntity> jeuEntities = Collections.singletonList(jeuEntity);

        when(jeuJpaRepository.findByNomEndingWith(filtre)).thenReturn(jeuEntities);
        when(jeuMapper.entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class))).thenReturn(jeu);

        // Act
        final List<Jeu> result = jeuRepositoryAdapter.findByNomEndingWith(filtre);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(jeu, result.get(0));
        verify(jeuJpaRepository).findByNomEndingWith(filtre);
        verify(jeuMapper).entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testExistsByNom() {
        // Arrange
        when(jeuJpaRepository.existsByNom(jeuNom)).thenReturn(true);

        // Act
        final boolean result = jeuRepositoryAdapter.existsByNom(jeuNom);

        // Assert
        assertTrue(result);
        verify(jeuJpaRepository).existsByNom(jeuNom);
    }

    @Test
    void testCountByEditeur() {
        // Arrange
        final EditeurEntity editeurEntity2 = mock(EditeurEntity.class);
        final long expectedCount = 10L;

        when(editeurMapper.domainToEntity(eq(editeur), any(CycleAvoidingMappingContext.class))).thenReturn(editeurEntity2);
        when(jeuJpaRepository.countByEditeur(editeurEntity2)).thenReturn(expectedCount);

        // Act
        final long result = jeuRepositoryAdapter.countByEditeur(editeur);

        // Assert
        assertEquals(expectedCount, result);
        verify(editeurMapper).domainToEntity(eq(editeur), any(CycleAvoidingMappingContext.class));
        verify(jeuJpaRepository).countByEditeur(editeurEntity2);
    }

    @Test
    void testFindByPlateformes() {
        // Arrange
        final List<JeuEntity> jeuEntities = Collections.singletonList(jeuEntity);
        final PlateformeEntity plateformeEntity2 = mock(PlateformeEntity.class);

        when(plateformeMapper.domainToEntity(eq(plateforme), any(CycleAvoidingMappingContext.class))).thenReturn(plateformeEntity2);
        when(jeuJpaRepository.findByPlateformes(plateformeEntity2)).thenReturn(jeuEntities);
        when(jeuMapper.entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class))).thenReturn(jeu);

        // Act
        final List<Jeu> result = jeuRepositoryAdapter.findByPlateformes(plateforme);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(jeu, result.get(0));
        verify(plateformeMapper).domainToEntity(eq(plateforme), any(CycleAvoidingMappingContext.class));
        verify(jeuJpaRepository).findByPlateformes(plateformeEntity2);
        verify(jeuMapper).entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindByNomLikeAndDateDeSortieBetween() {
        // Arrange
        final List<JeuEntity> jeuEntities = Collections.singletonList(jeuEntity);

        when(jeuJpaRepository.findByNomLikeAndDateDeSortieBetween(jeuNom, dateDebut, dateFin)).thenReturn(jeuEntities);
        when(jeuMapper.entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class))).thenReturn(jeu);

        // Act
        final List<Jeu> result = jeuRepositoryAdapter.findByNomLikeAndDateDeSortieBetween(jeuNom, dateDebut, dateFin);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(jeu, result.get(0));
        verify(jeuJpaRepository).findByNomLikeAndDateDeSortieBetween(jeuNom, dateDebut, dateFin);
        verify(jeuMapper).entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindGamesRandomlySorted() {
        // Arrange
        final List<JeuEntity> jeuEntities = Collections.singletonList(jeuEntity);

        when(jeuJpaRepository.findGamesRandomlySorted()).thenReturn(jeuEntities);
        when(jeuMapper.entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class))).thenReturn(jeu);

        // Act
        final List<Jeu> result = jeuRepositoryAdapter.findGamesRandomlySorted();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(jeu, result.get(0));
        verify(jeuJpaRepository).findGamesRandomlySorted();
        verify(jeuMapper).entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindByNom() {
        // Arrange
        when(jeuJpaRepository.findByNom(jeuNom)).thenReturn(Optional.of(jeuEntity));
        when(jeuMapper.entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class))).thenReturn(jeu);

        // Act
        final Optional<Jeu> result = jeuRepositoryAdapter.findByNom(jeuNom);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(jeu, result.get());
        verify(jeuJpaRepository).findByNom(jeuNom);
        verify(jeuMapper).entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindByNomIgnoreCase() {
        // Arrange
        when(jeuJpaRepository.findByNomIgnoreCase(jeuNom)).thenReturn(Optional.of(jeuEntity));
        when(jeuMapper.entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class))).thenReturn(jeu);

        // Act
        final Optional<Jeu> result = jeuRepositoryAdapter.findByNomIgnoreCase(jeuNom);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(jeu, result.get());
        verify(jeuJpaRepository).findByNomIgnoreCase(jeuNom);
        verify(jeuMapper).entityToDomain(eq(jeuEntity), any(CycleAvoidingMappingContext.class));
    }
}