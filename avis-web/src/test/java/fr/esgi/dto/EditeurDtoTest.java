package fr.esgi.dto;

import org.junit.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class EditeurDtoTest {

    @Test
    public void testConstructorAndGetters() {
        // ARRANGE
        final EditeurDto dto = new EditeurDto(1L, "name");

        // ASSERT
        assertEquals(1L, dto.getId());
        assertEquals("name", dto.getNom());
    }

    @Test
    public void testEqualityAndHashCode() {
        // ARRANGE
        final EditeurDto dto1 = new EditeurDto(1L, "name");
        final EditeurDto dto2 = new EditeurDto(1L, "name");

        // ASSERT
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testToString() {
        // ARRANGE
        final EditeurDto dto = new EditeurDto(1L, "name");
        // ASSERT
        assertEquals("EditeurDto{id=1, nom='name'}", dto.toString());

    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        // ARRANGE
        final EditeurDto original = new EditeurDto(1L, "name");
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);

        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        final ObjectInputStream ois = new ObjectInputStream(bais);
        final EditeurDto desirialized = (EditeurDto) ois.readObject();
        // ASSERT
        assertEquals(original, desirialized);
    }
}