package fr.esgi.mapper;

import fr.esgi.entity.PlateformeEntity;
import fr.esgi.model.Plateforme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlateformeMapperTest {

    private PlateformeMapper plateformeMapper;

    private final CycleAvoidingMappingContext cycleAvoidingMappingContext = new CycleAvoidingMappingContext();

    @BeforeEach
    void setUp() {
        plateformeMapper = Mappers.getMapper(PlateformeMapper.class);
    }

    @Test
    void testEntityToDomain() {
        // Arrange
        final PlateformeEntity plateformeEntity = new PlateformeEntity();
        plateformeEntity.setId(1L);
        plateformeEntity.setNom("Plateforme Test");
        plateformeEntity.setDateDeSortie(LocalDate.now());

        // Act
        final Plateforme plateforme = plateformeMapper.entityToDomain(plateformeEntity, cycleAvoidingMappingContext);

        // Assert
        assertNotNull(plateforme);
        assertEquals(1L, plateforme.getId());
        assertEquals("Plateforme Test", plateforme.getNom());
        assertEquals(LocalDate.now(), plateforme.getDateDeSortie());
    }

    @Test
    void testDomainToEntity() {
        // Arrange
        final Plateforme plateforme = new Plateforme();
        plateforme.setId(1L);
        plateforme.setNom("Plateforme Test");
        plateforme.setDateDeSortie(LocalDate.now());

        // Act
        final PlateformeEntity plateformeEntity = plateformeMapper.domainToEntity(plateforme, cycleAvoidingMappingContext);

        // Assert
        assertNotNull(plateformeEntity);
        assertEquals(1L, plateformeEntity.getId());
        assertEquals("Plateforme Test", plateformeEntity.getNom());
        assertEquals(LocalDate.now(), plateformeEntity.getDateDeSortie());
    }

    @Test
    void testEntityToDomainWithNull() {
        // Act
        final Plateforme plateforme = plateformeMapper.entityToDomain(null, cycleAvoidingMappingContext);

        // Assert
        assertNull(plateforme);
    }

    @Test
    void testDomainToEntityWithNull() {
        // Act
        final PlateformeEntity plateformeEntity = plateformeMapper.domainToEntity(null, cycleAvoidingMappingContext);

        // Assert
        assertNull(plateformeEntity);
    }
}
