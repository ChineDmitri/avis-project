package fr.esgi.model.page;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginationParamsTest {

    @Test
    void testConstructor() {
        // Arrange
        final int offset = 0;
        final int limit = 10;
        // Act
        final PaginationParams paginationParams = new PaginationParams(offset, limit);
        // Assert
        assertEquals(offset, paginationParams.getOffset());
        assertEquals(limit, paginationParams.getLimit());
    }

    @Test
    void testMethodeOf(){
        // Arrange
        final int offset = 0;
        final int limit = 10;

        // Act
        final PaginationParams paginationParams = PaginationParams.of(offset, limit);

        // Assertions
        assertEquals(PaginationParams.class, paginationParams.getClass());
    }
}
