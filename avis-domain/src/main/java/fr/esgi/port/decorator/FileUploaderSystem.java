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

    @Override
    public String upload(InputStream io) {
        try {
            // Permanent upload directory
            String uploadDir = "/uploads/";
            Path uploadPath = Paths.get(uploadDir);

            // Create the directory if it doesn't exist
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate a unique filename
            String filename = System.currentTimeMillis() + ".jpg";
            Path filePath = uploadPath.resolve(filename);

            // Save the file
            Files.copy(io, filePath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("[LOG] PATH : " + filePath.toFile().getAbsolutePath());
            // Return the relative path for access
            return "/uploads/" + filename;
        } catch (IOException e) {
            System.err.println("Error uploading file: " + e.getMessage());
            return StringUtils.EMPTY;
        }
    }

}
