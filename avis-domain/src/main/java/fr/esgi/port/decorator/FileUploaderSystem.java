package fr.esgi.port.decorator;

import fr.esgi.port.FileUploader;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@AllArgsConstructor
public class FileUploaderSystem implements FileUploader {

    private String uploadDir;

    public FileUploaderSystem() {
        this.uploadDir = "/uploads/"; // Default directory
    }

    @Override
    public String upload(InputStream io) {
        try {
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String filename = System.currentTimeMillis() + ".jpg";
            Path filePath = uploadPath.resolve(filename);

            Files.copy(io, filePath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("[LOG] PATH : " + filePath.toAbsolutePath());

            return "/" + Paths.get(uploadDir).getFileName() + "/" + filename;
        } catch (IOException e) {
            System.err.println("Error uploading file: " + e.getMessage());
            return StringUtils.EMPTY;
        }
    }
}

