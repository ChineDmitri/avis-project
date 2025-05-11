package fr.esgi.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TechnicalExceptionTest {

    private static final String ERROR_MESSAGE = "Test message";

    @Test
    void testConstructorWithMessage() {
        TechnicalException exception = new TechnicalException(ERROR_MESSAGE);

        assertEquals(ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void testExceptionHierarchy() {
        TechnicalException exception = new TechnicalException(ERROR_MESSAGE);
        assertTrue(exception instanceof Exception);
    }

    @Test
    void testThrowAndCatch() {
        try {
            throwTechnicalException();
            fail("Expected TechnicalException was not thrown");
        } catch (TechnicalException e) {
            assertEquals(ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    void testExceptionInheritance() {
        try {
            throwTechnicalException();
            fail("Expected Exception was not thrown");
        } catch (Exception e) {
            assertTrue(e instanceof TechnicalException);
            assertEquals(ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    void testStackTraceGeneration() {
        TechnicalException exception = new TechnicalException(ERROR_MESSAGE);

        StackTraceElement[] stackTrace = exception.getStackTrace();
        assertNotNull(stackTrace);
        assertTrue(stackTrace.length > 0);

        boolean foundTestMethod = false;
        for (StackTraceElement element : stackTrace) {
            if (element.getMethodName().contains("testStackTraceGeneration")) {
                foundTestMethod = true;
                break;
            }
        }
        assertTrue(foundTestMethod, "Stack trace should contain the current test method");
    }

    @Test
    void testCauseHandling() {
        IllegalArgumentException cause = new IllegalArgumentException("Invalid argument");
        Exception wrappedException = new Exception("Intermediate exception", cause);

        try {
            try {
                throw wrappedException;
            } catch (Exception e) {
                throw new TechnicalException("Infrastructure error occurred");
            }
        } catch (TechnicalException e) {
            assertEquals("Infrastructure error occurred", e.getMessage());

            assertNull(e.getCause());
        }
    }

    private void throwTechnicalException() throws TechnicalException {
        throw new TechnicalException(ERROR_MESSAGE);
    }
}