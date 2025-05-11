package fr.esgi.decorator;

import fr.esgi.port.FileUploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class FilesS3BucketDecoratorTest {

    private FileUploader delegate;
    private FilesS3BucketDecorator decorator;

    @BeforeEach
    void setUp() {
        delegate = mock(FileUploader.class);
        decorator = new FilesS3BucketDecorator(delegate);
    }

    @Test
    void upload_shouldLogAndDelegateCall() {
        // Arrange
        InputStream input = new ByteArrayInputStream("dummy data".getBytes());
        when(delegate.upload(any(InputStream.class))).thenReturn("/uploads/test.jpg");

        // Act
        String result = decorator.upload(input);

        // Assert
        assertThat(result).isEqualTo("/uploads/test.jpg");
        verify(delegate, times(1)).upload(any(InputStream.class));
    }
}
