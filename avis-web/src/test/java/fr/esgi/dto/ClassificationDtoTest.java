package fr.esgi.dto;

import org.junit.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class ClassificationDtoTest {

    @Test
    public void testConstructorAndGetters() {
        // ARRANGE
        final ClassificationDto dto = new ClassificationDto(1L, "name", "#FFFFF");
        // ASSERT
        assertEquals(1L, dto.getId());
        assertEquals("name", dto.getNom());
        assertEquals("#FFFFF", dto.getCouleurRGB());
    }

    @Test
    public void testEqualityAndHashCode() {
        // ARRANGE
        final ClassificationDto dto1 = new ClassificationDto(1L, "name", "#FFFFF");
        final ClassificationDto dto2 = new ClassificationDto(1L, "name", "#FFFFF");
        // ASSERT
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testToString() {
        // ARRANGE
        final ClassificationDto dto = new ClassificationDto(1L, "name", "#FFFFF");
        final String expected = "ClassificationDto(id=1, nom=name, couleurRGB=#FFFFF)";
        // ASSERT
        assertEquals(expected, dto.toString());
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        // ARRANGE
        final ClassificationDto original = new ClassificationDto(1L, "name", "#FFFFF");
        final ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        final ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
        objOut.writeObject(original);

        final ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        final ObjectInputStream objIn = new ObjectInputStream(byteIn);
        final ClassificationDto deserialized = (ClassificationDto) objIn.readObject();

        assertEquals(original, deserialized);
    }
}