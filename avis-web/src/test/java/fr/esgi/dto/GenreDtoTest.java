package fr.esgi.dto;

import org.junit.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class GenreDtoTest {

    @Test
    public void testConstructorAndGetters() {
        // ARRANGE
        final GenreDto dto = new GenreDto(1L, "Aventure");

        // ASSERT
        assertEquals(1L, dto.getId());
        assertEquals("Aventure", dto.getNom());
    }

    @Test
    public void testEqualityAndHashCode() {
        // ARRANGE
        final GenreDto dto1 = new GenreDto(1L, "Aventure");
        final GenreDto dto2 = new GenreDto(1L, "Aventure");

        // ASSERT
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testToString() {
        // ARRANGE
        final GenreDto dto = new GenreDto(1L, "Aventure");

        // ASSERT
        assertEquals("GenreDto(id=1, nom=Aventure)", dto.toString());
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        // ARRANGE
        final GenreDto original = new GenreDto(1L, "Aventure");
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);

        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        final ObjectInputStream ois = new ObjectInputStream(bais);
        final GenreDto deserialized = (GenreDto) ois.readObject();

        // ASSERT
        assertEquals(original, deserialized);
    }
}
