package fr.esgi.adapter;

import fr.esgi.entity.AvisEntity;
import fr.esgi.mapper.AvisMapper;
import fr.esgi.model.Avis;
import fr.esgi.repository.AvisJpaRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AvisRepositoryAdapterTest {

    @Mock
    private AvisJpaRepository avisJpaRepository;

    @Mock
    private AvisMapper avisMapper;

    @InjectMocks
    private AvisRepositoryAdapter avisRepositoryAdapter;

    private Avis avis;
    private AvisEntity avisEntity;
    private Long avisId;

    @BeforeEach
    void setUp() {
        avisId = 1L;
        avis = new Avis(); // You might need to set more properties on this object
        avisEntity = new AvisEntity(); // You might need to set more properties on this object
    }

    @Test
    void testSave() {
        // Arrange
        when(avisMapper.domainToEntity(avis)).thenReturn(avisEntity);
        when(avisJpaRepository.save(avisEntity)).thenReturn(avisEntity);
        when(avisMapper.entityToDomain(avisEntity)).thenReturn(avis);

        // Act
        final Avis result = avisRepositoryAdapter.save(avis);

        // Assert
        assertNotNull(result);
        assertEquals(avis, result);
        verify(avisMapper).domainToEntity(avis);
        verify(avisJpaRepository).save(avisEntity);
        verify(avisMapper).entityToDomain(avisEntity);
    }

    @Test
    void testFindAll() {
        // Arrange
        final List<AvisEntity> avisEntities = Collections.singletonList(avisEntity);
        when(avisJpaRepository.findAll()).thenReturn(avisEntities);
        when(avisMapper.entityToDomain(avisEntity)).thenReturn(avis);

        // Act
        final List<Avis> result = avisRepositoryAdapter.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(avis, result.get(0));
        verify(avisJpaRepository).findAll();
        verify(avisMapper).entityToDomain(avisEntity);
    }

    @Test
    void testFindById_WhenExists() {
        // Arrange
        when(avisJpaRepository.findById(avisId)).thenReturn(Optional.of(avisEntity));
        when(avisMapper.entityToDomain(avisEntity)).thenReturn(avis);

        // Act
        final Optional<Avis> result = avisRepositoryAdapter.findById(avisId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(avis, result.get());
        verify(avisJpaRepository).findById(avisId);
        verify(avisMapper).entityToDomain(avisEntity);
    }

    @Test
    void testFindById_WhenNotExists() {
        // Arrange
        when(avisJpaRepository.findById(avisId)).thenReturn(Optional.empty());

        // Act
        final Optional<Avis> result = avisRepositoryAdapter.findById(avisId);

        // Assert
        assertFalse(result.isPresent());
        verify(avisJpaRepository).findById(avisId);
        verify(avisMapper, never()).entityToDomain(any());
    }

    @Test
    void testExistsById_WhenExists() {
        // Arrange
        when(avisJpaRepository.existsById(avisId)).thenReturn(true);

        // Act
        final boolean result = avisRepositoryAdapter.existsById(avisId);

        // Assert
        assertTrue(result);
        verify(avisJpaRepository).existsById(avisId);
    }

    @Test
    void testExistsById_WhenNotExists() {
        // Arrange
        when(avisJpaRepository.existsById(avisId)).thenReturn(false);

        // Act
        final boolean result = avisRepositoryAdapter.existsById(avisId);

        // Assert
        assertFalse(result);
        verify(avisJpaRepository).existsById(avisId);
    }

    @Test
    void testDeleteById() {
        // Arrange - nothing to do
        doNothing().when(avisJpaRepository).deleteById(avisId);

        // Act
        avisRepositoryAdapter.deleteById(avisId);

        // Assert
        verify(avisJpaRepository).deleteById(avisId);
    }
}