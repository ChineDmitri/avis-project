package fr.esgi.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DomainExceptionTest {

    private static final String ERROR_MESSAGE = "Domain validation error";
    private static final String CAUSE_MESSAGE = "Original cause";

    /**
     * Implementation concrète pour les tests
     */
    private static class TestDomainException extends DomainException {
        public TestDomainException(String message) {
            super(message);
        }

        public TestDomainException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @Test
    void testConstructorWithMessage() {
        DomainException exception = new TestDomainException(ERROR_MESSAGE);

        assertEquals(ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        IllegalArgumentException cause = new IllegalArgumentException(CAUSE_MESSAGE);

        DomainException exception = new TestDomainException(ERROR_MESSAGE, cause);

        assertEquals(ERROR_MESSAGE, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertEquals(CAUSE_MESSAGE, exception.getCause().getMessage());
    }

    @Test
    void testExceptionHierarchy() {
        DomainException exception = new TestDomainException(ERROR_MESSAGE);
        assertTrue(exception instanceof RuntimeException);

        assertTrue(exception instanceof RuntimeException);
        assertFalse(exception instanceof Exception && !(exception instanceof RuntimeException));
    }

    @Test
    void testAbstractNature() {
        DomainException exception = new TestDomainException(ERROR_MESSAGE);
        assertNotNull(exception);
    }

    @Test
    void testExceptionInheritance() {
        try {
            throw new TestDomainException(ERROR_MESSAGE);
        } catch (RuntimeException e) {
            assertTrue(e instanceof DomainException);
            assertEquals(ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    void testPolymorphicBehavior() {
        class SubTypeOne extends DomainException {
            public SubTypeOne(String message) {
                super(message);
            }
        }

        class SubTypeTwo extends DomainException {
            public SubTypeTwo(String message) {
                super(message);
            }
        }

        DomainException ex1 = new SubTypeOne("Subtype one error");
        DomainException ex2 = new SubTypeTwo("Subtype two error");

        assertEquals("Subtype one error", ex1.getMessage());
        assertEquals("Subtype two error", ex2.getMessage());

        assertTrue(ex1 instanceof DomainException);
        assertTrue(ex2 instanceof DomainException);

        assertNotEquals(ex1.getClass(), ex2.getClass());
    }

    @Test
    void testStackTraceGeneration() {
        DomainException exception = new TestDomainException(ERROR_MESSAGE);

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
    void testCauseChaining() {
        IllegalArgumentException rootCause = new IllegalArgumentException("Root cause");
        Exception intermediateCause = new Exception("Intermediate cause", rootCause);
        DomainException exception = new TestDomainException("Domain error", intermediateCause);

        assertEquals("Domain error", exception.getMessage());
        assertEquals(intermediateCause, exception.getCause());
        assertEquals("Intermediate cause", exception.getCause().getMessage());
        assertEquals(rootCause, exception.getCause().getCause());
        assertEquals("Root cause", exception.getCause().getCause().getMessage());
    }

    @Test
    void testThrowsInMethod() {
        assertThrows(DomainException.class, () -> {
            methodThatThrowsDomainException();
        });
    }

    private void methodThatThrowsDomainException() {
        throw new TestDomainException(ERROR_MESSAGE);
    }
}