package fr.esgi.dto;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static fr.esgi.utils.avisWebUtils.createJeuDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JeuDtoTest {

    @Test
    void testConstructorAndGetters() {
        // ARRANGE
        final EditeurDto editeur = new EditeurDto(1L, "Editeur");
        final GenreDto genre = new GenreDto(1L, "Action");
        final ClassificationDto classification = new ClassificationDto(1L, "PEGI", "#FFFFF");

        final PlateformeDto plateforme1 = new PlateformeDto("PC", "12/12/2001", new ArrayList<>());
        final PlateformeDto plateforme2 = new PlateformeDto("Xbox", "11/11/2001", new ArrayList<>());
        final JeuDto dto = new JeuDto(1L, "GameName", editeur, genre, classification, "Game Description",
                LocalDate.of(2023, 5, 10), Arrays.asList(plateforme1, plateforme2));

        // ASSERT
        assertEquals(1L, dto.getId());
        assertEquals("GameName", dto.getNom());
        assertEquals(editeur, dto.getEditeur());
        assertEquals(genre, dto.getGenre());
        assertEquals(classification, dto.getClassification());
        assertEquals("Game Description", dto.getDescription());
        assertEquals(LocalDate.of(2023, 5, 10), dto.getDateDeSortie());
        assertEquals(2, dto.getPlateformes().size());
    }

    @Test
    void testEqualityAndHashCode() {
        // ARRANGE
        final JeuDto dto1 = createJeuDto();
        final JeuDto dto2 = createJeuDto();
        // ASSERT
        assertEquals(dto1, dto2);  // Test equality based on fields
        assertEquals(dto1.hashCode(), dto2.hashCode());  // Test hashCode based on fields
    }

    @Test
    void testToString() {
        // ARRANGE
        final JeuDto jeuDto = createJeuDto();
        // ASSERT
        assertEquals("JeuDto(id=1, nom=GameName, editeur=EditeurDto(id=1, nom=Editeur), genre=GenreDto(id=1, nom=Action), classification=ClassificationDto(id=1, nom=PEGI, couleurRGB=#FFFFF), description=Game Description, dateDeSortie=2025-05-13, plateformes=[PlateformeDto(nom=PC, dateDeSortie=12/12/2001, jeux=[]), PlateformeDto(nom=Xbox, dateDeSortie=11/11/2001, jeux=[])])", jeuDto.toString());
    }

    @Test
    void testSerialization() throws IOException, ClassNotFoundException {
        // ARRANGE
        final JeuDto original = createJeuDto();

        // Serialize
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);

        // Deserialize
        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        final ObjectInputStream ois = new ObjectInputStream(bais);
        final JeuDto deserialized = (JeuDto) ois.readObject();

        // ASSERT
        assertEquals(original, deserialized);
    }
}
