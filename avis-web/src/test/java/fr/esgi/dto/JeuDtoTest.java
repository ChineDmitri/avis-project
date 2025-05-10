package fr.esgi.dto;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class JeuDtoTest {

    @Test
    public void testConstructorAndGetters() {
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
        assertEquals(LocalDate.of(2023, 5, 10), dto.getDateSortie());
        assertEquals(2, dto.getPlateformes().size());
    }

    @Test
    public void testEqualityAndHashCode() {
        // ARRANGE
        final EditeurDto editeur = new EditeurDto(1L, "Editeur");
        final GenreDto genre = new GenreDto(1L, "Action");
        final ClassificationDto classification = new ClassificationDto(1L, "PEGI", "#FFFFF");

        final PlateformeDto plateforme1 = new PlateformeDto("PC", "12/12/2001", new ArrayList<>());
        final PlateformeDto plateforme2 = new PlateformeDto("Xbox", "11/11/2001", new ArrayList<>());
        JeuDto dto1 = new JeuDto(1L, "GameName", editeur, genre, classification, "Game Description",
                LocalDate.of(2023, 5, 10), Arrays.asList(plateforme1, plateforme2));
        JeuDto dto2 = new JeuDto(1L, "GameName", editeur, genre, classification, "Game Description",
                LocalDate.of(2023, 5, 10), Arrays.asList(plateforme1, plateforme2));

        // ASSERT
        assertEquals(dto1, dto2);  // Test equality based on fields
        assertEquals(dto1.hashCode(), dto2.hashCode());  // Test hashCode based on fields
    }

    @Test
    public void testToString() {
        // ARRANGE
        final EditeurDto editeur = new EditeurDto(1L, "Editeur");
        final GenreDto genre = new GenreDto(1L, "Action");
        final ClassificationDto classification = new ClassificationDto(1L, "PEGI", "#FFFFF");

        final PlateformeDto plateforme1 = new PlateformeDto("PC", "12/12/2001", new ArrayList<>());
        final PlateformeDto plateforme2 = new PlateformeDto("Xbox", "11/11/2001", new ArrayList<>());
        JeuDto dto = new JeuDto(1L, "GameName", editeur, genre, classification, "Game Description",
                LocalDate.of(2023, 5, 10), Arrays.asList(plateforme1, plateforme2));

        // ASSERT
        assertEquals("JeuDto(id=1, nom=GameName, editeur=EditeurDto(id=1, nom=Editeur), genre=GenreDto(id=1, nom=Action), classification=ClassificationDto(id=1, nom=PEGI, couleurRGB=#FFFFF), description=Game Description, dateSortie=2023-05-10, plateformes=[PlateformeDto(nom=PC, dateDeSortie=12/12/2001, jeux=[]), PlateformeDto(nom=Xbox, dateDeSortie=11/11/2001, jeux=[])])", dto.toString());
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        // ARRANGE
        final EditeurDto editeur = new EditeurDto(1L, "Editeur");
        final GenreDto genre = new GenreDto(1L, "Action");
        final ClassificationDto classification = new ClassificationDto(1L, "PEGI", "#FFFFF");

        final PlateformeDto plateforme1 = new PlateformeDto("PC", "12/12/2001", new ArrayList<>());
        final PlateformeDto plateforme2 = new PlateformeDto("Xbox", "11/11/2001", new ArrayList<>());
        JeuDto original = new JeuDto(1L, "GameName", editeur, genre, classification, "Game Description",
                LocalDate.of(2023, 5, 10), Arrays.asList(plateforme1, plateforme2));

        // Serialize
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);

        // Deserialize
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        JeuDto deserialized = (JeuDto) ois.readObject();

        // ASSERT
        assertEquals(original, deserialized);
    }
}
