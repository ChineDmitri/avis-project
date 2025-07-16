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
        // Utiliser un répertoire dans le répertoire de travail de l'application
        this.uploadDir = "uploads"; // Répertoire relatif sans "/" au début
    }

    @Override
    public String upload(InputStream io) {
        try {
            // Créer le répertoire uploads dans le répertoire de travail de l'application
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                System.out.println("[LOG] Répertoire créé : " + uploadPath.toAbsolutePath());
            }

            String filename = System.currentTimeMillis() + ".jpg";
            Path filePath = uploadPath.resolve(filename);

            Files.copy(io, filePath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("[LOG] Fichier sauvegardé : " + filePath.toAbsolutePath());

            // Retourner le chemin relatif pour l'accès web
            return "uploads/" + filename;
        } catch (IOException e) {
            System.err.println("Error uploading file: " + e.getMessage());
            e.printStackTrace();
            return StringUtils.EMPTY;
        }
    }
}

