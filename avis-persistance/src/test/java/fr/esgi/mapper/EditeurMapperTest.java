package fr.esgi.mapper;

import fr.esgi.entity.EditeurEntity;
import fr.esgi.model.Editeur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EditeurMapperTest {

    private EditeurMapper editeurMapper;

    @Spy
    private final CycleAvoidingMappingContext cycleAvoidingMappingContext = new CycleAvoidingMappingContext();

    @BeforeEach
    void setUp() {
        editeurMapper = Mappers.getMapper(EditeurMapper.class);
    }

    @Test
    void testEntityToDomain() {
        // Arrange
        final EditeurEntity editeurEntity = new EditeurEntity();
        editeurEntity.setId(1L);
        editeurEntity.setNom("Editeur Test");


        // Act
        final Editeur editeur = editeurMapper.entityToDomain(editeurEntity, cycleAvoidingMappingContext);

        // Assert
        assertNotNull(editeur);
        assertEquals(1L, editeur.getId());
        assertEquals("Editeur Test", editeur.getNom());
    }

    @Test
    void  testDomainToEntity(){
        // Arrange
        final Editeur editeur = new Editeur();
        editeur.setId(1L);
        editeur.setNom("Editeur Test");

        // Act
        final EditeurEntity editeurEntity = editeurMapper.domainToEntity(editeur, cycleAvoidingMappingContext);

        // Assert
        assertNotNull(editeurEntity);
        assertEquals(1L, editeurEntity.getId());
        assertEquals("Editeur Test", editeurEntity.getNom());
    }

    @Test
    void testEntityToDomainWithNull() {
        // Act
        final Editeur editeur = editeurMapper.entityToDomain(null, cycleAvoidingMappingContext);

        // Assert
        assertNull(editeur);
    }

    @Test
    void testDomainToEntityWithNull() {
        // Act
        final EditeurEntity editeurEntity = editeurMapper.domainToEntity(null, cycleAvoidingMappingContext);

        // Assert
        assertNull(editeurEntity);
    }
}
