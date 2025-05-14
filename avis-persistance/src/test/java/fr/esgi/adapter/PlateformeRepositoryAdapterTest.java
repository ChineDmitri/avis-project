package fr.esgi.adapter;

import fr.esgi.entity.PlateformeEntity;
import fr.esgi.mapper.CycleAvoidingMappingContext;
import fr.esgi.mapper.PlateformeMapper;
import fr.esgi.model.Plateforme;
import fr.esgi.repository.PlateformeJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlateformeRepositoryAdapterTest {

    @Mock
    private PlateformeJpaRepository plateformeJpaRepository;

    @Mock
    private PlateformeMapper plateformeMapper;

    @InjectMocks
    private PlateformeRepositoryAdapter plateformeRepositoryAdapter;

    private Plateforme plateforme;
    private PlateformeEntity plateformeEntity;
    private String plateformeNom;
    private String plateformeFiltre;

    @BeforeEach
    void setUp() {
        plateformeNom = "PlayStation 5";
        plateformeFiltre = "PlayStation";
        plateforme = new Plateforme(); // You might need to set more properties
        plateformeEntity = new PlateformeEntity(); // You might need to set more properties
    }

    @Test
    void testFindByNomContaining() {
        // Arrange
        final List<PlateformeEntity> plateformeEntities = Collections.singletonList(plateformeEntity);
        when(plateformeJpaRepository.findByNomContaining(plateformeFiltre)).thenReturn(plateformeEntities);
        when(plateformeMapper.entityToDomain(eq(plateformeEntity), any(CycleAvoidingMappingContext.class)))
                .thenReturn(plateforme);

        // Act
        final List<Plateforme> result = plateformeRepositoryAdapter.findByNomContaining(plateformeFiltre);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(plateforme, result.get(0));
        verify(plateformeJpaRepository).findByNomContaining(plateformeFiltre);
        verify(plateformeMapper).entityToDomain(eq(plateformeEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindByNom() {
        // Arrange
        when(plateformeJpaRepository.findByNom(plateformeNom)).thenReturn(plateformeEntity);
        when(plateformeMapper.entityToDomain(eq(plateformeEntity), any(CycleAvoidingMappingContext.class)))
                .thenReturn(plateforme);

        // Act
        final Plateforme result = plateformeRepositoryAdapter.findByNom(plateformeNom);

        // Assert
        assertNotNull(result);
        assertEquals(plateforme, result);
        verify(plateformeJpaRepository).findByNom(plateformeNom);
        verify(plateformeMapper).entityToDomain(eq(plateformeEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testDelete() {
        // Arrange
        when(plateformeMapper.domainToEntity(eq(plateforme), any(CycleAvoidingMappingContext.class)))
                .thenReturn(plateformeEntity);
        doNothing().when(plateformeJpaRepository).delete(plateformeEntity);

        // Act
        plateformeRepositoryAdapter.delete(plateforme);

        // Assert
        verify(plateformeMapper).domainToEntity(eq(plateforme), any(CycleAvoidingMappingContext.class));
        verify(plateformeJpaRepository).delete(plateformeEntity);
    }

    @Test
    void testSave() {
        // Arrange
        when(plateformeMapper.domainToEntity(eq(plateforme), any(CycleAvoidingMappingContext.class)))
                .thenReturn(plateformeEntity);
        when(plateformeJpaRepository.save(plateformeEntity)).thenReturn(plateformeEntity);
        when(plateformeMapper.entityToDomain(eq(plateformeEntity), any(CycleAvoidingMappingContext.class)))
                .thenReturn(plateforme);

        // Act
        final Plateforme result = plateformeRepositoryAdapter.save(plateforme);

        // Assert
        assertNotNull(result);
        assertEquals(plateforme, result);
        verify(plateformeMapper).domainToEntity(eq(plateforme), any(CycleAvoidingMappingContext.class));
        verify(plateformeJpaRepository).save(plateformeEntity);
        verify(plateformeMapper).entityToDomain(eq(plateformeEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindAll() {
        // Arrange
        final List<PlateformeEntity> plateformeEntities = Collections.singletonList(plateformeEntity);
        when(plateformeJpaRepository.findAll()).thenReturn(plateformeEntities);
        when(plateformeMapper.entityToDomain(eq(plateformeEntity), any(CycleAvoidingMappingContext.class)))
                .thenReturn(plateforme);

        // Act
        final List<Plateforme> result = plateformeRepositoryAdapter.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(plateforme, result.get(0));
        verify(plateformeJpaRepository).findAll();
        verify(plateformeMapper).entityToDomain(eq(plateformeEntity), any(CycleAvoidingMappingContext.class));
    }

    @Test
    void testFindByNomContaining_EmptyResult() {
        // Arrange
        when(plateformeJpaRepository.findByNomContaining(plateformeFiltre)).thenReturn(List.of());

        // Act
        final List<Plateforme> result = plateformeRepositoryAdapter.findByNomContaining(plateformeFiltre);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(plateformeJpaRepository).findByNomContaining(plateformeFiltre);
        verify(plateformeMapper, never()).entityToDomain(any(), any());
    }

    @Test
    void testFindByNom_NullResult() {
        // Arrange
        when(plateformeJpaRepository.findByNom(plateformeNom)).thenReturn(null);

        // This test actually depends on how your mapper handles null values.
        // If it should throw an exception, you'd test for that instead.
        when(plateformeMapper.entityToDomain(eq(null), any(CycleAvoidingMappingContext.class)))
                .thenReturn(null);

        // Act
        final Plateforme result = plateformeRepositoryAdapter.findByNom(plateformeNom);

        // Assert
        assertNull(result);
        verify(plateformeJpaRepository).findByNom(plateformeNom);
        verify(plateformeMapper).entityToDomain(eq(null), any(CycleAvoidingMappingContext.class));
    }
}