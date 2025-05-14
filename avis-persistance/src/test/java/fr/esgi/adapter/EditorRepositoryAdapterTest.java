package fr.esgi.adapter;

import fr.esgi.entity.EditeurEntity;
import fr.esgi.mapper.CycleAvoidingMappingContext;
import fr.esgi.mapper.EditeurMapper;
import fr.esgi.mapper.JeuMapper;
import fr.esgi.model.Editeur;
import fr.esgi.model.Jeu;
import fr.esgi.repository.EditeurJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EditorRepositoryAdapterTest {

    @Mock
    private EditeurJpaRepository editeurJpaRepository;

    @Mock
    private EditeurMapper editeurMapper;

    @Mock
    private JeuMapper jeuMapper;

    @InjectMocks
    private EditorRepositoryAdapter editorRepositoryAdapter;

    private Editeur editeur;
    private EditeurEntity editeurEntity;
    private Jeu jeu;
    private Long editeurId;
    private String editeurNom;

    @BeforeEach
    void setUp() {
        editeurId = 1L;
        editeurNom = "Test Editeur";
        editeur = new Editeur(); // You might need to set more properties
        editeurEntity = new EditeurEntity(); // You might need to set more properties
        jeu = new Jeu(); // You might need to set more properties
    }

    @Test
    void testFindByNomContainingIgnoreCase() {
        // Arrange
        final List<EditeurEntity> editeurEntities = Collections.singletonList(editeurEntity);
        when(editeurJpaRepository.findByNomContainingIgnoreCase(editeurNom)).thenReturn(editeurEntities);
        when(editeurMapper.entityToDomain(eq(editeurEntity), any(CycleAvoidingMappingContext.class))).thenReturn(editeur);

        // Act
        final List<Editeur> result = editorRepositoryAdapter.findByNomContainingIgnoreCase(editeurNom);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(editeur, result.get(0));
        verify(editeurJpaRepository).findByNomContainingIgnoreCase(editeurNom);
        verify(editeurMapper).entityToDomain(eq(editeurEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindByNom_WhenExists() {
        // Arrange
        when(editeurJpaRepository.findByNom(editeurNom)).thenReturn(editeurEntity);
        when(editeurMapper.entityToDomain(eq(editeurEntity), any(CycleAvoidingMappingContext.class))).thenReturn(editeur);

        // Act
        final Editeur result = editorRepositoryAdapter.findByNom(editeurNom);

        // Assert
        assertNotNull(result);
        assertEquals(editeur, result);
        verify(editeurJpaRepository).findByNom(editeurNom);
        verify(editeurMapper).entityToDomain(eq(editeurEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindByNom_WhenNotExists() {
        // Arrange
        when(editeurJpaRepository.findByNom(editeurNom)).thenReturn(null);

        // Act
        final Editeur result = editorRepositoryAdapter.findByNom(editeurNom);

        // Assert
        assertNull(result);
        verify(editeurJpaRepository).findByNom(editeurNom);
        verify(editeurMapper, never()).entityToDomain(any(), any());
    }

    @Test
    void testFindByNomIgnoreCase_WhenExists() {
        // Arrange
        when(editeurJpaRepository.findByNomIgnoreCase(editeurNom)).thenReturn(Optional.of(editeurEntity));
        when(editeurMapper.entityToDomain(eq(editeurEntity), any(CycleAvoidingMappingContext.class))).thenReturn(editeur);

        // Act
        final Optional<Editeur> result = editorRepositoryAdapter.findByNomIgnoreCase(editeurNom);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(editeur, result.get());
        verify(editeurJpaRepository).findByNomIgnoreCase(editeurNom);
        verify(editeurMapper).entityToDomain(eq(editeurEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindByNomIgnoreCase_WhenNotExists() {
        // Arrange
        when(editeurJpaRepository.findByNomIgnoreCase(editeurNom)).thenReturn(Optional.empty());

        // Act
        final Optional<Editeur> result = editorRepositoryAdapter.findByNomIgnoreCase(editeurNom);

        // Assert
        assertFalse(result.isPresent());
        verify(editeurJpaRepository).findByNomIgnoreCase(editeurNom);
        verify(editeurMapper, never()).entityToDomain(any(), any());
    }

    @Test
    void testSave() {
        // Arrange
        when(editeurMapper.domainToEntity(eq(editeur), any(CycleAvoidingMappingContext.class))).thenReturn(editeurEntity);
        when(editeurJpaRepository.save(editeurEntity)).thenReturn(editeurEntity);
        when(editeurMapper.entityToDomain(eq(editeurEntity), any(CycleAvoidingMappingContext.class))).thenReturn(editeur);

        // Act
        final Editeur result = editorRepositoryAdapter.save(editeur);

        // Assert
        assertNotNull(result);
        assertEquals(editeur, result);
        verify(editeurMapper).domainToEntity(eq(editeur), any(CycleAvoidingMappingContext.class));
        verify(editeurJpaRepository).save(editeurEntity);
        verify(editeurMapper).entityToDomain(eq(editeurEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindAll() {
        // Arrange
        final List<EditeurEntity> editeurEntities = Collections.singletonList(editeurEntity);
        when(editeurJpaRepository.findAll()).thenReturn(editeurEntities);
        when(editeurMapper.entityToDomain(eq(editeurEntity), any(CycleAvoidingMappingContext.class))).thenReturn(editeur);

        // Act
        final List<Editeur> result = editorRepositoryAdapter.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(editeur, result.get(0));
        verify(editeurJpaRepository).findAll();
        verify(editeurMapper).entityToDomain(eq(editeurEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindById_WhenExists() {
        // Arrange
        when(editeurJpaRepository.findById(editeurId)).thenReturn(Optional.of(editeurEntity));
        when(editeurMapper.entityToDomain(eq(editeurEntity), any(CycleAvoidingMappingContext.class))).thenReturn(editeur);

        // Act
        final Optional<Editeur> result = editorRepositoryAdapter.findById(editeurId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(editeur, result.get());
        verify(editeurJpaRepository).findById(editeurId);
        verify(editeurMapper).entityToDomain(eq(editeurEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindById_WhenNotExists() {
        // Arrange
        when(editeurJpaRepository.findById(editeurId)).thenReturn(Optional.empty());

        // Act
        final Optional<Editeur> result = editorRepositoryAdapter.findById(editeurId);

        // Assert
        assertFalse(result.isPresent());
        verify(editeurJpaRepository).findById(editeurId);
        verify(editeurMapper, never()).entityToDomain(any(), any());
    }

    @Test
    void testExistsById_WhenExists() {
        // Arrange
        when(editeurJpaRepository.existsById(editeurId)).thenReturn(true);

        // Act
        final boolean result = editorRepositoryAdapter.existsById(editeurId);

        // Assert
        assertTrue(result);
        verify(editeurJpaRepository).existsById(editeurId);
    }

    @Test
    void testExistsById_WhenNotExists() {
        // Arrange
        when(editeurJpaRepository.existsById(editeurId)).thenReturn(false);

        // Act
        final boolean result = editorRepositoryAdapter.existsById(editeurId);

        // Assert
        assertFalse(result);
        verify(editeurJpaRepository).existsById(editeurId);
    }

    @Test
    void testDeleteById() {
        // Arrange
        doNothing().when(editeurJpaRepository).deleteById(editeurId);

        // Act
        editorRepositoryAdapter.deleteById(editeurId);

        // Assert
        verify(editeurJpaRepository).deleteById(editeurId);
    }
}