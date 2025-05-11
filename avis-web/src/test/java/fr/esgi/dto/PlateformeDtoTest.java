package fr.esgi.dto;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlateformeDtoTest {

    @Test
    void testConstructorAndGetters() {
        // ARRANGE
        final JeuDto jeu1 = new JeuDto(1L, "Game1", null, null, null, "Description1", null, null);
        final JeuDto jeu2 = new JeuDto(2L, "Game2", null, null, null, "Description2", null, null);
        final PlateformeDto dto = new PlateformeDto("PC", "2023-05-10", Arrays.asList(jeu1, jeu2));

        // ASSERT
        assertEquals("PC", dto.getNom());
        assertEquals("2023-05-10", dto.getDateDeSortie());
        assertEquals(2, dto.getJeux().size());
    }

    @Test
    void testEqualityAndHashCode() {
        // ARRANGE
       final JeuDto jeu2 = new JeuDto(2L, "Game2", null, null, null, "Description2", null, null);
       final JeuDto jeu1 = new JeuDto(1L, "Game1", null, null, null, "Description1", null, null);
       final PlateformeDto dto1 = new PlateformeDto("PC", "2023-05-10", Arrays.asList(jeu1, jeu2));
       final PlateformeDto dto2 = new PlateformeDto("PC", "2023-05-10", Arrays.asList(jeu1, jeu2));

        // ASSERT
        assertEquals(dto1, dto2);  // Test equality based on fields
        assertEquals(dto1.hashCode(), dto2.hashCode());  // Test hashCode based on fields
    }

    @Test
    void testToString() {
        // ARRANGE
        final JeuDto jeu1 = new JeuDto(1L, "Game1", null, null, null, "Description1", null, null);
        final JeuDto jeu2 = new JeuDto(2L, "Game2", null, null, null, "Description2", null, null);
        final PlateformeDto dto = new PlateformeDto("PC", "2023-05-10", Arrays.asList(jeu1, jeu2));

        // ASSERT
        assertEquals("PlateformeDto(nom=PC, dateDeSortie=2023-05-10, jeux=[JeuDto(id=1, nom=Game1, editeur=null, genre=null, classification=null, description=Description1, dateDeSortie=null, plateformes=null), JeuDto(id=2, nom=Game2, editeur=null, genre=null, classification=null, description=Description2, dateDeSortie=null, plateformes=null)])", dto.toString());
    }

    @Test
    void testSerialization() throws IOException, ClassNotFoundException {
        // ARRANGE
        final JeuDto jeu1 = new JeuDto(1L, "Game1", null, null, null, "Description1", null, null);
        final JeuDto jeu2 = new JeuDto(2L, "Game2", null, null, null, "Description2", null, null);
        final PlateformeDto original = new PlateformeDto("PC", "2023-05-10", Arrays.asList(jeu1, jeu2));

        // Serialize
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);

        // Deserialize
        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        final ObjectInputStream ois = new ObjectInputStream(bais);
        final PlateformeDto deserialized = (PlateformeDto) ois.readObject();

        // ASSERT
        assertEquals(original, deserialized);
    }
}
