package fr.esgi.model.page;

import fr.esgi.model.Genre;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomPagedResultTest {


    @Test
    void testConstructorAndGetters(){
        // Arrange
        final List<Genre> genres = List.of(new Genre(1L, "MOBA", new ArrayList<>()));
        final int offest = 0;
        final int limit = 10;
        final long totalElements = 1L;

        // Act
        final CustomPagedResult<Genre> result = new CustomPagedResult<>(genres, offest, limit, totalElements);

        // Assert
        assertEquals(genres, result.getContent());
        assertEquals(offest, result.getOffset());
        assertEquals(limit, result.getLimit());
        assertEquals(totalElements, result.getTotalElements());
    }

    @Test
    void testHasNextWhenMultipleElements(){
        // Arrange
        final Genre genre = new Genre(1L, "MOBA", new ArrayList<>());
        final Genre genre2 = new Genre(2L, "Sports", new ArrayList<>());
        final List<Genre> genres = new ArrayList<>(Arrays.asList(genre, genre2));
        final int offest = 0;
        final int limit = 1;
        final long totalElements = 2L;

        // Act
        final CustomPagedResult<Genre> result = new CustomPagedResult<>(genres, offest, limit, totalElements);

        // Assertions
        assertEquals(2, result.getContent().size());
        assertTrue(result.hasNext());
    }

    @Test
    void testHasNextWhenOnlyOneElement(){
        // Arrange
        final List<Genre> genres = List.of(new Genre(1L, "MOBA", new ArrayList<>()));
        final int offest = 0;
        final int limit = 10;
        final long totalElements = 1L;

        // Act
        final CustomPagedResult<Genre> result = new CustomPagedResult<>(genres, offest, limit, totalElements);

        // Assert
        assertFalse(result.hasNext());
    }


    @Test
    void testHasNextWhenExactlyAtLimit() {
        // Arrange
        final List<Character> charContent = Arrays.asList('a', 'b', 'c');
        final int offset = 7;
        final int limit = 3;
        final long totalElements = 10;

        // Act
        final CustomPagedResult<Character> result = new CustomPagedResult<>(charContent, offset, limit, totalElements);

        // Assert
        assertFalse(result.hasNext());
    }

    @Test
    void testWithEmptyContent() {
        // Arrange
        final List<String> emptyContent = Collections.emptyList();
        final int offset = 0;
        final int limit = 10;
        final long totalElements = 0;
        // Act
        final CustomPagedResult<String> result = new CustomPagedResult<>(emptyContent, offset, limit, totalElements);

        // Assert
        assertTrue(result.getContent().isEmpty());
        assertEquals(offset, result.getOffset());
        assertEquals(limit, result.getLimit());
        assertEquals(totalElements, result.getTotalElements());
        assertFalse(result.hasNext());
    }

    @Test
    void testEdgeCaseWithNegativeValues() {
        // Arrange
        List<String> content = Arrays.asList("item1", "item2");
        int negativeOffset = -5;
        int negativeLimit = -10;
        long totalElements = 100;

        // Act
        CustomPagedResult<String> result = new CustomPagedResult<>(content, negativeOffset, negativeLimit, totalElements);

        // Assert
        assertEquals(negativeOffset, result.getOffset());
        assertEquals(negativeLimit, result.getLimit());
        assertTrue(result.hasNext());
    }
}