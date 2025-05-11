package fr.esgi.port.decorator;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class FileUploaderSystemTest {

    @TempDir
    Path tempDir;

    @Test
    void upload_shouldSaveFile_andReturnRelativePath() throws Exception {
        // Arrange
        String customUploadDir = tempDir.resolve("uploads").toString();
        FileUploaderSystem uploader = new FileUploaderSystem(customUploadDir);

        byte[] content = "test data".getBytes();
        InputStream inputStream = new ByteArrayInputStream(content);

        // Act
        String result = uploader.upload(inputStream);

        // Assert
        assertThat(result).startsWith("/uploads/").endsWith(".jpg");

        Path uploadedFile = tempDir.resolve(result.substring(1)); // remove leading '/'
        assertThat(Files.exists(uploadedFile)).isTrue();
        assertThat(Files.readAllBytes(uploadedFile)).isEqualTo(content);
    }

    @Test
    void upload_shouldReturnEmptyString_whenIOExceptionOccurs() {
        // Arrange
        FileUploaderSystem uploader = new FileUploaderSystem();

        InputStream failingInputStream = new InputStream() {
            @Override
            public int read() {
                throw new RuntimeException("forced failure");
            }
        };

        // Act
        String result = uploader.upload(failingInputStream);

        // Assert
        assertThat(result).isEqualTo(StringUtils.EMPTY);
    }
}
