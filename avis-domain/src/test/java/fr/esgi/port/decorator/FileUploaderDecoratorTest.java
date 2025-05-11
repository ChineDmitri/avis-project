package fr.esgi.port.decorator;

import fr.esgi.port.FileUploader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileUploaderDecoratorTest {

    private static class ConcreteFileUploaderDecorator extends FileUploaderDecorator {
        public ConcreteFileUploaderDecorator(FileUploader delegate) {
            super(delegate);
        }

    }

    @Mock
    private FileUploader mockDelegate;

    private FileUploaderDecorator decorator;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        decorator = new ConcreteFileUploaderDecorator(mockDelegate);

        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testConstructorWithNullDelegate() {
        assertDoesNotThrow(() -> new ConcreteFileUploaderDecorator(null));
    }

    @Test
    void testUploadCallsDelegateUpload() {
        InputStream testInput = new ByteArrayInputStream("test data".getBytes());
        when(mockDelegate.upload(any(InputStream.class))).thenReturn("test-file.txt");

        String result = decorator.upload(testInput);

        verify(mockDelegate).upload(testInput);

        assertEquals("test-file.txt", result);
    }

    @Test
    void testUploadLogsCorrectly() {
        InputStream testInput = new ByteArrayInputStream("test data".getBytes());
        when(mockDelegate.upload(any(InputStream.class))).thenReturn("test-file.txt");

        decorator.upload(testInput);

        String output = outContent.toString();
        assertTrue(output.contains("[LOG] Téléversement standard démarré"));
        assertTrue(output.contains("[LOG] Téléversement standard terminé. FileName: test-file.txt"));
    }

    @Test
    void testUploadWithNullInput() {
        when(mockDelegate.upload(null)).thenReturn("null-file.txt");

        String result = decorator.upload(null);

        verify(mockDelegate).upload(null);

        assertEquals("null-file.txt", result);
    }

    @Test
    void testUploadWhenDelegateThrowsException() {
        InputStream testInput = new ByteArrayInputStream("test data".getBytes());

        RuntimeException testException = new RuntimeException("Test exception");
        when(mockDelegate.upload(any(InputStream.class))).thenThrow(testException);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            decorator.upload(testInput);
        });

        assertSame(testException, exception);

        String output = outContent.toString();
        assertTrue(output.contains("[LOG] Téléversement standard démarré"));
        assertFalse(output.contains("[LOG] Téléversement standard terminé"));
    }

    @Test
    void testDecorationChain() {
        FileUploader baseUploader = mock(FileUploader.class);
        when(baseUploader.upload(any(InputStream.class))).thenReturn("original.txt");

        FileUploaderDecorator decorator1 = new ConcreteFileUploaderDecorator(baseUploader);
        FileUploaderDecorator decorator2 = new ConcreteFileUploaderDecorator(decorator1);

        InputStream testInput = new ByteArrayInputStream("test data".getBytes());
        String result = decorator2.upload(testInput);

        assertEquals("original.txt", result);

        verify(baseUploader).upload(testInput);

        String output = outContent.toString();
        assertEquals(2, output.split("\\[LOG\\] Téléversement standard démarré").length - 1);
        assertEquals(2, output.split("\\[LOG\\] Téléversement standard terminé").length - 1);
    }

    @Test
    void testPolymorphicBehavior() {
        InputStream testInput = new ByteArrayInputStream("test data".getBytes());
        when(mockDelegate.upload(any(InputStream.class))).thenReturn("test-file.txt");

        FileUploader uploader = decorator;
        String result = uploader.upload(testInput);

        assertEquals("test-file.txt", result);
        verify(mockDelegate).upload(testInput);
    }
}